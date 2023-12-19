package service2

import (
	"context"
	"log"
	"math"
	"net"
	"os"
	proto "prac10/go/proto/service2"
	"strconv"
	"sync"

	"google.golang.org/grpc"
	"google.golang.org/grpc/reflection"
)

type Server struct {
	proto.Service2Server
}

func (s *Server) ToBin(ctx context.Context, req *proto.ConvertRequest) (*proto.ConvertResponse, error) {
	message := req.GetMessage()

	num, err := strconv.Atoi(message)
	if err != nil {
		log.Println("Incorrect message type")
		return &proto.ConvertResponse{
			Answer: "Error",
		}, nil
	}

	num64 := int64(num)

	return &proto.ConvertResponse{
		Answer: strconv.FormatInt(num64, 2),
	}, nil
}

func (s *Server) ToDec(ctx context.Context, req *proto.ConvertRequest) (*proto.ConvertResponse, error) {
	message := req.GetMessage()
	len := len(message) - 1
	ans := 0

	for _, str := range message {
		if str == '1' {
			ans += int(math.Pow(2, float64(len)))
		} else if str != '0' {
			log.Println("Incorrect message type")
			return &proto.ConvertResponse{
				Answer: "Error",
			}, nil
		}
		len -= 1
	}

	return &proto.ConvertResponse{
		Answer: strconv.Itoa(ans),
	}, nil
}

func StartServer(wg *sync.WaitGroup) {
	defer wg.Done()

	f, err := os.OpenFile("./go/services/service2/log.log", os.O_RDWR|os.O_CREATE|os.O_APPEND, 0666)
	if err != nil {
		log.Fatal("Error opening log file: %v", err)
	}
	defer f.Close()
	log.SetOutput(f)
	log.Println("Starting service2 ...")

	lis, err := net.Listen("tcp", ":"+os.Getenv("SERVICE2_PORT"))
	if err != nil {
		log.Fatal(err)
	}

	s := grpc.NewServer()
	proto.RegisterService2Server(s, &Server{})
	reflection.Register(s)

	log.Println(s.Serve(lis))
}
