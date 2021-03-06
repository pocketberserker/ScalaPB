// Generated by the Scala Plugin for the Protocol Buffer Compiler.
// Do not edit!
//
// Protofile syntax: PROTO3

package com.google.protobuf.wrappers



@SerialVersionUID(0L)
final case class StringValue(
    value: String = ""
    ) extends com.trueaccord.scalapb.GeneratedMessage with com.trueaccord.scalapb.Message[StringValue] with com.trueaccord.lenses.Updatable[StringValue] {
    @transient
    lazy val serializedSize: Int = {
      var __size = 0
      if (value != "") { __size += com.google.protobuf.CodedOutputStream.computeStringSize(1, value) }
      __size
    }
    def writeTo(output: com.google.protobuf.CodedOutputStream): Unit = {
      {
        val __v = value
        if (__v != "") {
          output.writeString(1, __v)
        }
      };
    }
    def mergeFrom(__input: com.google.protobuf.CodedInputStream): com.google.protobuf.wrappers.StringValue = {
      var __value = this.value
      var _done__ = false
      while (!_done__) {
        val _tag__ = __input.readTag()
        _tag__ match {
          case 0 => _done__ = true
          case 10 =>
            __value = __input.readString()
          case tag => __input.skipField(tag)
        }
      }
      com.google.protobuf.wrappers.StringValue(
          value = __value
      )
    }
    def withValue(__v: String): StringValue = copy(value = __v)
    def getField(__field: com.google.protobuf.Descriptors.FieldDescriptor): scala.Any = {
      __field.getNumber match {
        case 1 => {
          val __t = value
          if (__t != "") __t else null
        }
      }
    }
    override def toString: String = com.trueaccord.scalapb.TextFormat.printToUnicodeString(this)
    def companion = com.google.protobuf.wrappers.StringValue
}

object StringValue extends com.trueaccord.scalapb.GeneratedMessageCompanion[StringValue]  {
  implicit def messageCompanion: com.trueaccord.scalapb.GeneratedMessageCompanion[StringValue]  = this
  def fromFieldsMap(__fieldsMap: Map[com.google.protobuf.Descriptors.FieldDescriptor, scala.Any]): com.google.protobuf.wrappers.StringValue = {
    require(__fieldsMap.keys.forall(_.getContainingType() == descriptor), "FieldDescriptor does not match message type.")
    val __fields = descriptor.getFields
    com.google.protobuf.wrappers.StringValue(
      __fieldsMap.getOrElse(__fields.get(0), "").asInstanceOf[String]
    )
  }
  def descriptor: com.google.protobuf.Descriptors.Descriptor = GoogleProtobufWrappersProto.descriptor.getMessageTypes.get(7)
  def messageCompanionForField(__field: com.google.protobuf.Descriptors.FieldDescriptor): com.trueaccord.scalapb.GeneratedMessageCompanion[_] = throw new MatchError(__field)
  def enumCompanionForField(__field: com.google.protobuf.Descriptors.FieldDescriptor): com.trueaccord.scalapb.GeneratedEnumCompanion[_] = throw new MatchError(__field)
  lazy val defaultInstance = com.google.protobuf.wrappers.StringValue(
  )
  implicit class StringValueLens[UpperPB](_l: com.trueaccord.lenses.Lens[UpperPB, StringValue]) extends com.trueaccord.lenses.ObjectLens[UpperPB, StringValue](_l) {
    def value: com.trueaccord.lenses.Lens[UpperPB, String] = field(_.value)((c_, f_) => c_.copy(value = f_))
  }
  final val VALUE_FIELD_NUMBER = 1
}
