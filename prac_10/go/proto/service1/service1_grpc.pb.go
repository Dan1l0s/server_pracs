// Code generated by protoc-gen-go-grpc. DO NOT EDIT.
// versions:
// - protoc-gen-go-grpc v1.3.0
// - protoc             v4.25.1
// source: proto/service1/service1.proto

package service1

import (
	context "context"
	grpc "google.golang.org/grpc"
	codes "google.golang.org/grpc/codes"
	status "google.golang.org/grpc/status"
)

// This is a compile-time assertion to ensure that this generated file
// is compatible with the grpc package it is being compiled against.
// Requires gRPC-Go v1.32.0 or later.
const _ = grpc.SupportPackageIsVersion7

const (
	Service1_SayHello_FullMethodName = "/service1.service1/SayHello"
	Service1_SayBye_FullMethodName   = "/service1.service1/SayBye"
)

// Service1Client is the client API for Service1 service.
//
// For semantics around ctx use and closing/ending streaming RPCs, please refer to https://pkg.go.dev/google.golang.org/grpc/?tab=doc#ClientConn.NewStream.
type Service1Client interface {
	SayHello(ctx context.Context, in *HelloRequest, opts ...grpc.CallOption) (*HelloResponse, error)
	SayBye(ctx context.Context, in *HelloRequest, opts ...grpc.CallOption) (*HelloResponse, error)
}

type service1Client struct {
	cc grpc.ClientConnInterface
}

func NewService1Client(cc grpc.ClientConnInterface) Service1Client {
	return &service1Client{cc}
}

func (c *service1Client) SayHello(ctx context.Context, in *HelloRequest, opts ...grpc.CallOption) (*HelloResponse, error) {
	out := new(HelloResponse)
	err := c.cc.Invoke(ctx, Service1_SayHello_FullMethodName, in, out, opts...)
	if err != nil {
		return nil, err
	}
	return out, nil
}

func (c *service1Client) SayBye(ctx context.Context, in *HelloRequest, opts ...grpc.CallOption) (*HelloResponse, error) {
	out := new(HelloResponse)
	err := c.cc.Invoke(ctx, Service1_SayBye_FullMethodName, in, out, opts...)
	if err != nil {
		return nil, err
	}
	return out, nil
}

// Service1Server is the server API for Service1 service.
// All implementations must embed UnimplementedService1Server
// for forward compatibility
type Service1Server interface {
	SayHello(context.Context, *HelloRequest) (*HelloResponse, error)
	SayBye(context.Context, *HelloRequest) (*HelloResponse, error)
	mustEmbedUnimplementedService1Server()
}

// UnimplementedService1Server must be embedded to have forward compatible implementations.
type UnimplementedService1Server struct {
}

func (UnimplementedService1Server) SayHello(context.Context, *HelloRequest) (*HelloResponse, error) {
	return nil, status.Errorf(codes.Unimplemented, "method SayHello not implemented")
}
func (UnimplementedService1Server) SayBye(context.Context, *HelloRequest) (*HelloResponse, error) {
	return nil, status.Errorf(codes.Unimplemented, "method SayBye not implemented")
}
func (UnimplementedService1Server) mustEmbedUnimplementedService1Server() {}

// UnsafeService1Server may be embedded to opt out of forward compatibility for this service.
// Use of this interface is not recommended, as added methods to Service1Server will
// result in compilation errors.
type UnsafeService1Server interface {
	mustEmbedUnimplementedService1Server()
}

func RegisterService1Server(s grpc.ServiceRegistrar, srv Service1Server) {
	s.RegisterService(&Service1_ServiceDesc, srv)
}

func _Service1_SayHello_Handler(srv interface{}, ctx context.Context, dec func(interface{}) error, interceptor grpc.UnaryServerInterceptor) (interface{}, error) {
	in := new(HelloRequest)
	if err := dec(in); err != nil {
		return nil, err
	}
	if interceptor == nil {
		return srv.(Service1Server).SayHello(ctx, in)
	}
	info := &grpc.UnaryServerInfo{
		Server:     srv,
		FullMethod: Service1_SayHello_FullMethodName,
	}
	handler := func(ctx context.Context, req interface{}) (interface{}, error) {
		return srv.(Service1Server).SayHello(ctx, req.(*HelloRequest))
	}
	return interceptor(ctx, in, info, handler)
}

func _Service1_SayBye_Handler(srv interface{}, ctx context.Context, dec func(interface{}) error, interceptor grpc.UnaryServerInterceptor) (interface{}, error) {
	in := new(HelloRequest)
	if err := dec(in); err != nil {
		return nil, err
	}
	if interceptor == nil {
		return srv.(Service1Server).SayBye(ctx, in)
	}
	info := &grpc.UnaryServerInfo{
		Server:     srv,
		FullMethod: Service1_SayBye_FullMethodName,
	}
	handler := func(ctx context.Context, req interface{}) (interface{}, error) {
		return srv.(Service1Server).SayBye(ctx, req.(*HelloRequest))
	}
	return interceptor(ctx, in, info, handler)
}

// Service1_ServiceDesc is the grpc.ServiceDesc for Service1 service.
// It's only intended for direct use with grpc.RegisterService,
// and not to be introspected or modified (even as a copy)
var Service1_ServiceDesc = grpc.ServiceDesc{
	ServiceName: "service1.service1",
	HandlerType: (*Service1Server)(nil),
	Methods: []grpc.MethodDesc{
		{
			MethodName: "SayHello",
			Handler:    _Service1_SayHello_Handler,
		},
		{
			MethodName: "SayBye",
			Handler:    _Service1_SayBye_Handler,
		},
	},
	Streams:  []grpc.StreamDesc{},
	Metadata: "proto/service1/service1.proto",
}
