package service1

import (
	"context"
	proto "prac10/go/proto/service1"
	"net"
	"sync"
	"os"
	"log"

	"google.golang.org/grpc"
	"google.golang.org/grpc/reflection"
)

type Server struct {
	proto.Service1Server
}

func (s *Server) SayHello(ctx context.Context, req *proto.HelloRequest) (*proto.HelloResponse, error) {
	message := req.GetMessage()

	return &proto.HelloResponse{
		Answer: "Hello, " + message,
	}, nil
}

func (s *Server) SayBye(ctx context.Context, req *proto.HelloRequest) (*proto.HelloResponse, error) {
	message := req.GetMessage()

	return &proto.HelloResponse{
		Answer: "Good bye, " + message,
	}, nil
}

func StartServer(wg *sync.WaitGroup) {
	defer wg.Done()

	f, err := os.OpenFile("./go/services/service1/log.log", os.O_RDWR | os.O_CREATE | os.O_APPEND, 0666)
	if err != nil {
		log.Fatal("error opening log file: %v", err)
	}
	defer f.Close()
	log.SetOutput(f)
	log.Println("Starting service1 ...")

	lis, err := net.Listen("tcp", ":"+os.Getenv("SERVICE1_PORT"))
	if (err!=nil) {
		log.Fatal(err)
	}
	s := grpc.NewServer()
	proto.RegisterService1Server(s, &Server{})
	reflection.Register(s)

	log.Println(s.Serve(lis))
}
