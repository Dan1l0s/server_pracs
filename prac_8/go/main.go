package main

import (
	"encoding/json"
	"log"
	"net/http"
	"os"
	"prac8/encryption"
	"time"

	"github.com/gorilla/mux"
)

var err error

type Cookie struct {
	Name   string `json:"id"`
	Title  string `json:"value"`
}

func setCookies(w http.ResponseWriter, r *http.Request) {
	w.Header().Set("Content-Type", "application/json")

	var c Cookie
	_ = json.NewDecoder(r.Body).Decode(&c)
    cookie := &http.Cookie{
        Name:      c.Name,
        Value:     c.Title,
        Path:      "/",
		MaxAge:    360000,
		HttpOnly:  true,
		Secure:    true,
    }

	err = encryption.Encrypt(cookie)
	if err!=nil {
		log.Println("Cannot encrypt ", err)
		w.Write([]byte("Cannot encrypt"))
		return
	}
		
	http.SetCookie(w, cookie)
}

func getAllCookies(w http.ResponseWriter, r *http.Request) {
	go getAllCookiesTemp(w, r, "thread:\n")
	getAllCookiesTemp(w, r, "default:\n")
}

func getAllCookiesTemp(w http.ResponseWriter, r *http.Request, n string) {
	w.Header().Set("Content-Type", "application/json")
	
	for _, c := range r.Cookies() {
		err = encryption.Decrypt(c)
		if err!=nil {
			log.Println("Cannot decrypt ", err)
			w.Write([]byte("Cannot decrypt"))
			return
		}
		var cookie Cookie
		cookie.Name=c.Name
		cookie.Title=c.Value
		w.Write([]byte(n))
		json.NewEncoder(w).Encode(cookie)
		time.Sleep(time.Second)
    }
}

func getCookies(w http.ResponseWriter, r *http.Request) {
	params := mux.Vars(r)
	b := false
	for _, c := range r.Cookies() {
		if c.Name == params["name"] {
			err = encryption.Decrypt(c)
			if err!=nil {
				log.Println("Cannot decrypt ", err)
				w.Write([]byte("Cannot decrypt"))
				return
			}
			json.NewEncoder(w).Encode(c)
			b = true
		}
    }
	if b==false {
		w.Write([]byte("no such cookie"))
		log.Println("no such cookie")
	}
}
  
func main() {
    r := mux.NewRouter()

	encryption.MakeKey()

    r.HandleFunc("/api/cookie", getAllCookies).Methods("GET")
    r.HandleFunc("/api/cookie/{name}", getCookies).Methods("GET")
    r.HandleFunc("/api/cookie", setCookies).Methods("POST")

	f, err := os.OpenFile("log.log", os.O_RDWR | os.O_CREATE | os.O_APPEND, 0666)
	if err != nil {
		log.Fatalf("error opening file: %v", err)
	}
	defer f.Close()

	log.SetOutput(f)
	log.Println("Starting server...")
	
    log.Fatal(http.ListenAndServe(":80", r))
}