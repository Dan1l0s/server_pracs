package main

import (
	"log"
	"net/http"
	"os"
	"prac9/api"
	"github.com/gorilla/mux"
	"github.com/joho/godotenv"
)

var err error

func main() {
    r := mux.NewRouter()

	err := godotenv.Load(".env")
	if err != nil {
		log.Fatal("Some error occured. Err: %s", err)
	}

	f, err := os.OpenFile("log.log", os.O_RDWR | os.O_CREATE | os.O_APPEND, 0666)
	if err != nil {
		log.Fatal("error opening log file: %v", err)
	}
	defer f.Close()

	log.SetOutput(f)
	log.Println("Starting server...")

	r.HandleFunc("/files", api.GetFiles).Methods("GET")
	r.HandleFunc("/files/{id}", api.GetFile).Methods("GET")
	r.HandleFunc("/files/{id}/info", api.GetFileInfo).Methods("GET")
	r.HandleFunc("/files", api.SaveFile).Methods("POST")
	r.HandleFunc("/files/{id}", api.UpdateFile).Methods("PUT")
	r.HandleFunc("/files/{id}", api.DeleteFile).Methods("DELETE")
	r.HandleFunc("/files/{id}/download", api.DownloadFile).Methods("GET")

	log.Fatal(http.ListenAndServe(os.Getenv("WEB_SERVER_INNER_PORT"), r))
}