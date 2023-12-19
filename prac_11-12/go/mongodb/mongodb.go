package mongodb

import (
	"context"
	"go.mongodb.org/mongo-driver/bson"
	"go.mongodb.org/mongo-driver/bson/primitive"
	"go.mongodb.org/mongo-driver/mongo"
	"go.mongodb.org/mongo-driver/mongo/gridfs"
	"go.mongodb.org/mongo-driver/mongo/options"
	"io"
)

type Storage struct {
	DB *mongo.Database
}

type FileInfo struct {
	Name   string `bson:"filename"`
	Length int64  `bson:"length"`
	Id     string `bson:"_id"`
}

// New метод инициализации подключения к базе данных
func New(path, dbname string) (*Storage, error) {
	client, err := mongo.Connect(context.TODO(), options.Client().ApplyURI(path))
	db := client.Database(dbname)
	return &Storage{DB: db}, err
}

// SaveFile метод сохранения файла в базу данных
func (s *Storage) SaveFile(file io.Reader, filename string) (string, error) {
	bucket, err := gridfs.NewBucket(s.DB)
	if err != nil {
		return "", err
	}

	uploadOpts := options.GridFSUpload().SetMetadata(bson.D{{"metadata tag", "first"}})
	objectID, err := bucket.UploadFromStream(filename, file, uploadOpts)
	if err != nil {
		return "", err
	}
	return objectID.Hex(), nil
}

// GetFileById метод получения файла по id из базы данных
func (s *Storage) GetFileById(id string) (io.Reader, error) {
	objId, err := primitive.ObjectIDFromHex(id)
	if err != nil {
		return nil, err
	}

	bucket, err := gridfs.NewBucket(s.DB)
	if err != nil {
		return nil, err
	}

	downloadStream, err := bucket.OpenDownloadStream(objId)
	if err != nil {
		return nil, err
	}

	return downloadStream, nil
}

//обновление файоа
func (s *Storage) UpdateFileById(id string, file io.Reader, filename string) (error) {
	objId, err := primitive.ObjectIDFromHex(id)
	if err != nil {
		return err
	}

	bucket, err := gridfs.NewBucket(s.DB)
	if err != nil {
		return err
	}
	uploadOpts := options.GridFSUpload().SetMetadata(bson.D{{"metadata tag", "first"}})
	s.DeleteFileById(id)
	err = bucket.UploadFromStreamWithID(objId, filename, file, uploadOpts)
	if err != nil {
		return err
	}

	return nil
}

// GetFileInfoById метод получения информации о файле по id из базы данных
func (s *Storage) GetFileInfoById(id string) (*FileInfo, error) {
	bucket, err := gridfs.NewBucket(s.DB)
	if err != nil {
		return nil, err
	}

	objId, err := primitive.ObjectIDFromHex(id)
	if err != nil {
		return nil, err
	}
	filter := bson.D{{"_id", objId}}
	cursor, err := bucket.Find(filter)
	if err != nil {
		return nil, err
	}

	var foundFiles []FileInfo
	if err = cursor.All(context.TODO(), &foundFiles); err != nil {
		return nil, err
	}

	return &foundFiles[0], nil
}

// DeleteFileById метод удаления файла по id
func (s *Storage) DeleteFileById(id string) (error) {
	bucket, err := gridfs.NewBucket(s.DB)
	if err != nil {
		return err
	}

	objId, err := primitive.ObjectIDFromHex(id)
	if err != nil {
		return err
	}

	err = bucket.Delete(objId)

	return err
}

// GetFileList метод получения списка файлов
func (s *Storage) GetFileInfoList() ([]FileInfo, error) {
	bucket, err := gridfs.NewBucket(s.DB)
	if err != nil {
		return nil, err
	}

	filter := bson.D{{}}
	cursor, err := bucket.Find(filter)
	if err != nil {
		return nil, err
	}

	var foundFiles []FileInfo
	if err = cursor.All(context.TODO(), &foundFiles); err != nil {
		return nil, err
	}

	return foundFiles, nil
}
