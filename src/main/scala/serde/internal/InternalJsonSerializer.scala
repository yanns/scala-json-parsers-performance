package serde.internal
import java.nio.ByteOrder
import java.nio.charset.StandardCharsets

import akka.util.ByteString
import serde._

private[serde] class InternalJsonSerializer(formatter: Formatter) extends Serializer { self ⇒
  import InternalJsonSerializer._

  private var level = 0
  private val buffer = ByteString.newBuilder

  override def serializeBoolean(b: Boolean): Unit =
    if (b) buffer.putByte('t').putByte('r').putByte('u').putByte('e')
    else buffer.putByte('f').putByte('a').putByte('l').putByte('s').putByte('e')

  override def serializeString(s: String): Unit =
    buffer.putByte('"').putBytes(s.getBytes(StandardCharsets.UTF_8)).putByte('"')

  override def serializeInt(i: Int): Unit = {
    val q =
      if (i > 0) i
      else {
        buffer.putByte('-')
        -i
      }
    writeInt(q, digits)
  }

  private def writeInt(q0: Int, ds: Array[Short]): Unit = {
    if (q0 < 100) {
      if (q0 < 10) {
        buffer.putByte((q0 + '0').toByte)
      } else {
        val d = ds(q0)
        buffer.putByte((d >> 8).toByte).putByte(d.toByte)
      }
    } else {
      val q1 = (q0 * 1374389535L >> 37).toInt // divide positive int by 100
      val r1 = q0 - 100 * q1
      val d = ds(r1)
      writeInt(q1, ds)
      buffer.putByte((d >> 8).toByte).putByte(d.toByte)
    }
  }

  override def serializeNull(): Unit =
    buffer.putByte('n').putByte('u').putByte('l').putByte('l')

  private def writeNewLine(): Unit = {
    buffer.putByte('\n')
    if (formatter.ident) {
      var i = 0
      val total = level
      while (i < total) {
        buffer.putByte(' ').putByte(' ')
        i += 1
      }
    }
  }

  private def writeSeparator(): Unit = {
    if (formatter.space) buffer.putByte(' ')
  }

  override def serializeIterator[A](i: Iterator[A])(implicit serializeA: Serialize[A]): Unit = {
    level += 1
    buffer.putByte('[')
    writeNewLine()
    var first = true
    while (i.hasNext) {
      if (!first) buffer.putByte(',')
      writeNewLine()
      first = false
      serializeA.serialize(i.next(), self)
    }
    level -= 1
    writeNewLine()
    buffer.putByte(']')
  }

  override def serializeMap(): MapSerializer = {
    buffer.putByte('{')
    level += 1
    new MapSerializer {
      var first = true
      override def add[A](key: String, a: A)(implicit serializeA: Serialize[A]): Unit = {
        if (!first) buffer.putByte(',')
        first = false
        writeNewLine()
        serializeString(key)
        buffer.putByte(':')
        writeSeparator()
        serializeA.serialize(a, self)
      }
      override def end(): Unit = {
        level -= 1
        writeNewLine()
        buffer.putByte('}')
      }
    }
  }

  def result(): ByteString = buffer.result()

}


private object InternalJsonSerializer {
  private final val digits: Array[Short] = {
    val ds = new Array[Short](100)
    var i = 0
    var j = 0
    do {
      var k = 0
      do {
        ds(i) = (((j + '0') << 8) + (k + '0')).toShort
        i += 1
        k += 1
      } while (k < 10)
      j += 1
    } while (j < 10)
    ds
  }

}
