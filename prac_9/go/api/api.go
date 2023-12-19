package api

import (
	"log"
	"net/http"
	"encoding/json"
	"prac9/mongodb"
	"github.com/gorilla/mux"
	"io"
	"os"
	"github.com/joho/godotenv"
)

var err error
var database *mongodb.Storage

func SaveFile(w http.ResponseWriter, r *http.Request) {
	r.ParseMultipartForm(10 << 20)

	file, fileHeader, err := r.FormFile("File")
	if err!=nil {
		w.Write([]byte("error"))
		log.Fatal(err)
		return
	}

	id, err := database.SaveFile(file, fileHeader.Filename)
	if err!=nil {
		w.Write([]byte("error"))
		log.Println(err)
		return
	}
	w.Write([]byte("File was added, id: " + id))
}

func GetFiles(w http.ResponseWriter, r *http.Request) {
	fileInfoList, err := database.GetFileInfoList()
	if err!=nil {
		w.Write([]byte("error"))
		log.Println(err)
		return
	}
	json.NewEncoder(w).Encode(fileInfoList)
}

func GetFile(w http.ResponseWriter, r *http.Request) {
	params := mux.Vars(r)

	file, err := database.GetFileById(params["id"])
	if err!=nil {
		w.Write([]byte("error"))
		log.Println(err)
		return
	}

	fileBytes, err := io.ReadAll(file)
	if err!=nil {
		w.Write([]byte("error"))
		log.Println(err)
		return
	}

	_, err = w.Write(fileBytes)
	if err != nil {
		w.Write([]byte("error"))
		log.Println(err)
		return
	}
}

func GetFileInfo(w http.ResponseWriter, r *http.Request) {
	params := mux.Vars(r)

	fileinfo, err := database.GetFileInfoById(params["id"])
	if err!=nil {
		w.Write([]byte("error"))
		log.Println(err)
		return
	}
	if fileinfo == nil {
		return
	}
	json.NewEncoder(w).Encode(fileinfo)
}

func DeleteFile(w http.ResponseWriter, r *http.Request) {
	params := mux.Vars(r)

	err = database.DeleteFileById(params["id"])
	if err!=nil {
		w.Write([]byte("error"))
		log.Println(err)
		return
	}
	w.Write([]byte("deleted"))
}

func UpdateFile(w http.ResponseWriter, r *http.Request) {
	params := mux.Vars(r)
	r.ParseMultipartForm(10 << 20)

	file, fileHeader, err := r.FormFile("File")
	if err!=nil {
		w.Write([]byte("error"))
		log.Println(err)
		return 
	}

	err = database.UpdateFileById(params["id"], file, fileHeader.Filename)
	if err!=nil {
		w.Write([]byte("error"))
		log.Println(err)
		return
	}
	w.Write([]byte(params["id"]))

}

func DownloadFile(w http.ResponseWriter, r *http.Request) {
    params := mux.Vars(r)

    file, err := database.GetFileById(params["id"])
    if err!=nil {
        w.Write([]byte("error"))
        log.Println(err)
        return
    }
    info, err := database.GetFileInfoById(params["id"])
    filename := info.Name

    f, err := os.Create(filename)
    if err != nil {
        log.Println(err)
    }

    io.Copy(f, file)


    w.Header().Set("Content-Disposition", "attachment; filename="+filename)
    http.ServeFile(w, r, filename)

    f.Close()
}


func init() {
	err := godotenv.Load(".env")
	if err != nil {
		log.Fatal("Some error occured. Err: %s", err)
	}


	database, err = mongodb.New(os.Getenv("DB_PATH"), os.Getenv("DB_NAME"))
	if err!=nil {
		log.Fatal(err)
	}
}