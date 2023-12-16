FROM golang:1.19
WORKDIR ./app
COPY go.mod go.sum ./
RUN go mod download
COPY *.go ./
COPY ./encryption/encryption.go ./encryption/encryption.go
EXPOSE 8080
CMD ["go", "run", "main.go"]