package encryption

import (
	"crypto/aes"
	"crypto/cipher"
	"crypto/rand"
	"errors"
	"io"
	"encoding/base64"
	"log"
	"net/http"
)


var key []byte = make([]byte, 32)
var err error

func Encrypt(cookie *http.Cookie) (error) {
	block, err := aes.NewCipher(key)
	if err != nil {
		return err
	}

	gcm, err := cipher.NewGCM(block)
	if err != nil {
		return err
	}

	nonce := make([]byte, gcm.NonceSize())
	if _, err := io.ReadFull(rand.Reader, nonce); err != nil {
		return err
	}

	text := cookie.Value

	encrypted := gcm.Seal(nil, nonce, []byte(text), nil)
	encrypted = append(nonce, encrypted...)

	cookie.Value = base64.URLEncoding.EncodeToString(encrypted)
	
	return nil
}

func Decrypt(cookie *http.Cookie) (error) {
	var data []byte

	data, err  = base64.URLEncoding.DecodeString(cookie.Value)
	if err != nil {
		return err
	}


	block, err := aes.NewCipher(key)
	if err != nil {
		return err
	}

	gcm, err := cipher.NewGCM(block)
	if err != nil {
		return err
	}

	if len(data) < gcm.NonceSize() {
		return errors.New("invalid data")
	}

	nonce := data[:gcm.NonceSize()]
	data = data[gcm.NonceSize():]

	decrypted, err := gcm.Open(nil, nonce, []byte(data), nil)
	if err != nil {
		return err
	}

	cookie.Value = string(decrypted[:])

	return nil
}

func MakeKey() {
	if _, err := io.ReadFull(rand.Reader, key); err != nil {
        log.Fatal(err)
    }
}
