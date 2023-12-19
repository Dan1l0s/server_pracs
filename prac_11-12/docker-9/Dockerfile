FROM golang:1.19
WORKDIR ./app
COPY ./go/go.mod ./go/go.sum ./
RUN go mod download
COPY ./go ./
COPY ./docker-9/.env ./
EXPOSE 8080
CMD ["go", "run", "main.go"]