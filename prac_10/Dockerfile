FROM golang:1.19
WORKDIR ./app
COPY go.mod go.sum ./
RUN go mod download
COPY main.go .env ./
COPY go ./go
EXPOSE 80
CMD ["go", "run", "main.go"]