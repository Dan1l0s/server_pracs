package main

import (
	"context"
	"net/http"
	"os"
	"sync"
	"log"

	"github.com/grpc-ecosystem/grpc-gateway/v2/runtime"
	"google.golang.org/grpc"
	"github.com/joho/godotenv"

	s1 "prac10/go/proto/service1"
	s2 "prac10/go/proto/service2"
	"prac10/go/services/service1"
	"prac10/go/services/service2"
)

func main() {

	f, err := os.OpenFile("log.log", os.O_RDWR | os.O_CREATE | os.O_APPEND, 0666)
	if err != nil {
		log.Fatal("error opening log file: %v", err)
	}
	defer f.Close()
	log.SetOutput(f)
	log.Println("Starting gateway server...")

	err = godotenv.Load(".env")
	if err != nil {
		log.Fatal("Error occured with loading env file. Err: %s", err)
	}

	var wg sync.WaitGroup

	wg.Add(1)
	go service1.StartServer(&wg)

	wg.Add(1)
	go service2.StartServer(&wg)

	wg.Add(1)
	go func() {
		defer wg.Done()

		ctx := context.Background()
		ctx, cancel := context.WithCancel(ctx)
		defer cancel()

		mux := runtime.NewServeMux()
		opts := []grpc.DialOption{grpc.WithInsecure()}

		s1.RegisterService1HandlerFromEndpoint(ctx, mux, "localhost:"+os.Getenv("SERVICE1_PORT"), opts);

		s2.RegisterService2HandlerFromEndpoint(ctx, mux, "localhost:"+os.Getenv("SERVICE2_PORT"), opts)

		log.Println(http.ListenAndServe(":"+os.Getenv("GATEWAY_SERVER_INNER_PORT"), mux))
	}()

	wg.Wait()
}
