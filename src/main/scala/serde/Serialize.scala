package serde

trait SerializationError

// serialize

trait Serialize[A] {
  def serialize(a: A, serializer: Serializer): Unit
}

object Serialize {
  def apply[A]()(implicit a: Serialize[A]): Serialize[A] = a
}

trait DefaultSerialize {
  implicit val booleanSerialize: Serialize[Boolean] =
    (a, serializer) ⇒ serializer.serializeBoolean(a)

  implicit val stringSerialize: Serialize[String] =
    (s, serializer) ⇒ serializer.serializeString(s)

  implicit val intSerialize: Serialize[Int] =
    (i, serializer) ⇒ serializer.serializeInt(i)

  implicit def optionSerialize[A](implicit serializeA: Serialize[A]): Serialize[Option[A]] =
    (o, serializer) ⇒ o match {
      case None ⇒ serializer.serializeNull()
      case Some(a) ⇒ serializeA.serialize(a, serializer)
    }

  implicit def vectorSerialize[A](implicit serializeA: Serialize[A]): Serialize[Vector[A]] =
    (v, serializer) ⇒ serializer.serializeIterator(v.iterator)
}

object DefaultSerialize extends DefaultSerialize


