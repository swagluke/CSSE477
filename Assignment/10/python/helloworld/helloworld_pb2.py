# Generated by the protocol buffer compiler.  DO NOT EDIT!
# source: helloworld.proto

import sys
_b=sys.version_info[0]<3 and (lambda x:x) or (lambda x:x.encode('latin1'))
from google.protobuf import descriptor as _descriptor
from google.protobuf import message as _message
from google.protobuf import reflection as _reflection
from google.protobuf import symbol_database as _symbol_database
from google.protobuf import descriptor_pb2
# @@protoc_insertion_point(imports)

_sym_db = _symbol_database.Default()




DESCRIPTOR = _descriptor.FileDescriptor(
  name='helloworld.proto',
  package='helloworld',
  syntax='proto3',
  serialized_pb=_b('\n\x10helloworld.proto\x12\nhelloworld\"\x1c\n\x0cHelloRequest\x12\x0c\n\x04name\x18\x01 \x01(\t\"\x1d\n\nHelloReply\x12\x0f\n\x07message\x18\x01 \x01(\t\"\x1b\n\x0bTimeRequest\x12\x0c\n\x04name\x18\x01 \x01(\t\"\x1c\n\tTimeReply\x12\x0f\n\x07message\x18\x01 \x01(\t2\x95\x01\n\x07Greeter\x12>\n\x08sayHello\x12\x18.helloworld.HelloRequest\x1a\x16.helloworld.HelloReply\"\x00\x12J\n\x16PrintCurrentTimePython\x12\x17.helloworld.TimeRequest\x1a\x15.helloworld.TimeReply\"\x00\x42\x36\n\x1bio.grpc.examples.helloworldB\x0fHelloWorldProtoP\x01\xa2\x02\x03HLWb\x06proto3')
)
_sym_db.RegisterFileDescriptor(DESCRIPTOR)




_HELLOREQUEST = _descriptor.Descriptor(
  name='HelloRequest',
  full_name='helloworld.HelloRequest',
  filename=None,
  file=DESCRIPTOR,
  containing_type=None,
  fields=[
    _descriptor.FieldDescriptor(
      name='name', full_name='helloworld.HelloRequest.name', index=0,
      number=1, type=9, cpp_type=9, label=1,
      has_default_value=False, default_value=_b("").decode('utf-8'),
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      options=None),
  ],
  extensions=[
  ],
  nested_types=[],
  enum_types=[
  ],
  options=None,
  is_extendable=False,
  syntax='proto3',
  extension_ranges=[],
  oneofs=[
  ],
  serialized_start=32,
  serialized_end=60,
)


_HELLOREPLY = _descriptor.Descriptor(
  name='HelloReply',
  full_name='helloworld.HelloReply',
  filename=None,
  file=DESCRIPTOR,
  containing_type=None,
  fields=[
    _descriptor.FieldDescriptor(
      name='message', full_name='helloworld.HelloReply.message', index=0,
      number=1, type=9, cpp_type=9, label=1,
      has_default_value=False, default_value=_b("").decode('utf-8'),
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      options=None),
  ],
  extensions=[
  ],
  nested_types=[],
  enum_types=[
  ],
  options=None,
  is_extendable=False,
  syntax='proto3',
  extension_ranges=[],
  oneofs=[
  ],
  serialized_start=62,
  serialized_end=91,
)


_TIMEREQUEST = _descriptor.Descriptor(
  name='TimeRequest',
  full_name='helloworld.TimeRequest',
  filename=None,
  file=DESCRIPTOR,
  containing_type=None,
  fields=[
    _descriptor.FieldDescriptor(
      name='name', full_name='helloworld.TimeRequest.name', index=0,
      number=1, type=9, cpp_type=9, label=1,
      has_default_value=False, default_value=_b("").decode('utf-8'),
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      options=None),
  ],
  extensions=[
  ],
  nested_types=[],
  enum_types=[
  ],
  options=None,
  is_extendable=False,
  syntax='proto3',
  extension_ranges=[],
  oneofs=[
  ],
  serialized_start=93,
  serialized_end=120,
)


_TIMEREPLY = _descriptor.Descriptor(
  name='TimeReply',
  full_name='helloworld.TimeReply',
  filename=None,
  file=DESCRIPTOR,
  containing_type=None,
  fields=[
    _descriptor.FieldDescriptor(
      name='message', full_name='helloworld.TimeReply.message', index=0,
      number=1, type=9, cpp_type=9, label=1,
      has_default_value=False, default_value=_b("").decode('utf-8'),
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      options=None),
  ],
  extensions=[
  ],
  nested_types=[],
  enum_types=[
  ],
  options=None,
  is_extendable=False,
  syntax='proto3',
  extension_ranges=[],
  oneofs=[
  ],
  serialized_start=122,
  serialized_end=150,
)

DESCRIPTOR.message_types_by_name['HelloRequest'] = _HELLOREQUEST
DESCRIPTOR.message_types_by_name['HelloReply'] = _HELLOREPLY
DESCRIPTOR.message_types_by_name['TimeRequest'] = _TIMEREQUEST
DESCRIPTOR.message_types_by_name['TimeReply'] = _TIMEREPLY

HelloRequest = _reflection.GeneratedProtocolMessageType('HelloRequest', (_message.Message,), dict(
  DESCRIPTOR = _HELLOREQUEST,
  __module__ = 'helloworld_pb2'
  # @@protoc_insertion_point(class_scope:helloworld.HelloRequest)
  ))
_sym_db.RegisterMessage(HelloRequest)

HelloReply = _reflection.GeneratedProtocolMessageType('HelloReply', (_message.Message,), dict(
  DESCRIPTOR = _HELLOREPLY,
  __module__ = 'helloworld_pb2'
  # @@protoc_insertion_point(class_scope:helloworld.HelloReply)
  ))
_sym_db.RegisterMessage(HelloReply)

TimeRequest = _reflection.GeneratedProtocolMessageType('TimeRequest', (_message.Message,), dict(
  DESCRIPTOR = _TIMEREQUEST,
  __module__ = 'helloworld_pb2'
  # @@protoc_insertion_point(class_scope:helloworld.TimeRequest)
  ))
_sym_db.RegisterMessage(TimeRequest)

TimeReply = _reflection.GeneratedProtocolMessageType('TimeReply', (_message.Message,), dict(
  DESCRIPTOR = _TIMEREPLY,
  __module__ = 'helloworld_pb2'
  # @@protoc_insertion_point(class_scope:helloworld.TimeReply)
  ))
_sym_db.RegisterMessage(TimeReply)


DESCRIPTOR.has_options = True
DESCRIPTOR._options = _descriptor._ParseOptions(descriptor_pb2.FileOptions(), _b('\n\033io.grpc.examples.helloworldB\017HelloWorldProtoP\001\242\002\003HLW'))
try:
  # THESE ELEMENTS WILL BE DEPRECATED.
  # Please use the generated *_pb2_grpc.py files instead.
  import grpc
  from grpc.framework.common import cardinality
  from grpc.framework.interfaces.face import utilities as face_utilities
  from grpc.beta import implementations as beta_implementations
  from grpc.beta import interfaces as beta_interfaces


  class GreeterStub(object):
    """The greeting service definition.
    """

    def __init__(self, channel):
      """Constructor.

      Args:
        channel: A grpc.Channel.
      """
      self.sayHello = channel.unary_unary(
          '/helloworld.Greeter/sayHello',
          request_serializer=HelloRequest.SerializeToString,
          response_deserializer=HelloReply.FromString,
          )
      self.PrintCurrentTimePython = channel.unary_unary(
          '/helloworld.Greeter/PrintCurrentTimePython',
          request_serializer=TimeRequest.SerializeToString,
          response_deserializer=TimeReply.FromString,
          )


  class GreeterServicer(object):
    """The greeting service definition.
    """

    def sayHello(self, request, context):
      """Sends a greeting
      """
      context.set_code(grpc.StatusCode.UNIMPLEMENTED)
      context.set_details('Method not implemented!')
      raise NotImplementedError('Method not implemented!')

    def PrintCurrentTimePython(self, request, context):
      """Sends current time python
      """
      context.set_code(grpc.StatusCode.UNIMPLEMENTED)
      context.set_details('Method not implemented!')
      raise NotImplementedError('Method not implemented!')


  def add_GreeterServicer_to_server(servicer, server):
    rpc_method_handlers = {
        'sayHello': grpc.unary_unary_rpc_method_handler(
            servicer.sayHello,
            request_deserializer=HelloRequest.FromString,
            response_serializer=HelloReply.SerializeToString,
        ),
        'PrintCurrentTimePython': grpc.unary_unary_rpc_method_handler(
            servicer.PrintCurrentTimePython,
            request_deserializer=TimeRequest.FromString,
            response_serializer=TimeReply.SerializeToString,
        ),
    }
    generic_handler = grpc.method_handlers_generic_handler(
        'helloworld.Greeter', rpc_method_handlers)
    server.add_generic_rpc_handlers((generic_handler,))


  class BetaGreeterServicer(object):
    """The Beta API is deprecated for 0.15.0 and later.

    It is recommended to use the GA API (classes and functions in this
    file not marked beta) for all further purposes. This class was generated
    only to ease transition from grpcio<0.15.0 to grpcio>=0.15.0."""
    """The greeting service definition.
    """
    def sayHello(self, request, context):
      """Sends a greeting
      """
      context.code(beta_interfaces.StatusCode.UNIMPLEMENTED)
    def PrintCurrentTimePython(self, request, context):
      """Sends current time python
      """
      context.code(beta_interfaces.StatusCode.UNIMPLEMENTED)


  class BetaGreeterStub(object):
    """The Beta API is deprecated for 0.15.0 and later.

    It is recommended to use the GA API (classes and functions in this
    file not marked beta) for all further purposes. This class was generated
    only to ease transition from grpcio<0.15.0 to grpcio>=0.15.0."""
    """The greeting service definition.
    """
    def sayHello(self, request, timeout, metadata=None, with_call=False, protocol_options=None):
      """Sends a greeting
      """
      raise NotImplementedError()
    sayHello.future = None
    def PrintCurrentTimePython(self, request, timeout, metadata=None, with_call=False, protocol_options=None):
      """Sends current time python
      """
      raise NotImplementedError()
    PrintCurrentTimePython.future = None


  def beta_create_Greeter_server(servicer, pool=None, pool_size=None, default_timeout=None, maximum_timeout=None):
    """The Beta API is deprecated for 0.15.0 and later.

    It is recommended to use the GA API (classes and functions in this
    file not marked beta) for all further purposes. This function was
    generated only to ease transition from grpcio<0.15.0 to grpcio>=0.15.0"""
    request_deserializers = {
      ('helloworld.Greeter', 'PrintCurrentTimePython'): TimeRequest.FromString,
      ('helloworld.Greeter', 'sayHello'): HelloRequest.FromString,
    }
    response_serializers = {
      ('helloworld.Greeter', 'PrintCurrentTimePython'): TimeReply.SerializeToString,
      ('helloworld.Greeter', 'sayHello'): HelloReply.SerializeToString,
    }
    method_implementations = {
      ('helloworld.Greeter', 'PrintCurrentTimePython'): face_utilities.unary_unary_inline(servicer.PrintCurrentTimePython),
      ('helloworld.Greeter', 'sayHello'): face_utilities.unary_unary_inline(servicer.sayHello),
    }
    server_options = beta_implementations.server_options(request_deserializers=request_deserializers, response_serializers=response_serializers, thread_pool=pool, thread_pool_size=pool_size, default_timeout=default_timeout, maximum_timeout=maximum_timeout)
    return beta_implementations.server(method_implementations, options=server_options)


  def beta_create_Greeter_stub(channel, host=None, metadata_transformer=None, pool=None, pool_size=None):
    """The Beta API is deprecated for 0.15.0 and later.

    It is recommended to use the GA API (classes and functions in this
    file not marked beta) for all further purposes. This function was
    generated only to ease transition from grpcio<0.15.0 to grpcio>=0.15.0"""
    request_serializers = {
      ('helloworld.Greeter', 'PrintCurrentTimePython'): TimeRequest.SerializeToString,
      ('helloworld.Greeter', 'sayHello'): HelloRequest.SerializeToString,
    }
    response_deserializers = {
      ('helloworld.Greeter', 'PrintCurrentTimePython'): TimeReply.FromString,
      ('helloworld.Greeter', 'sayHello'): HelloReply.FromString,
    }
    cardinalities = {
      'PrintCurrentTimePython': cardinality.Cardinality.UNARY_UNARY,
      'sayHello': cardinality.Cardinality.UNARY_UNARY,
    }
    stub_options = beta_implementations.stub_options(host=host, metadata_transformer=metadata_transformer, request_serializers=request_serializers, response_deserializers=response_deserializers, thread_pool=pool, thread_pool_size=pool_size)
    return beta_implementations.dynamic_stub(channel, 'helloworld.Greeter', cardinalities, options=stub_options)
except ImportError:
  pass
# @@protoc_insertion_point(module_scope)
