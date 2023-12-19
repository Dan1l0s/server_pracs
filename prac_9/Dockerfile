FROM golang:1.19
WORKDIR ./app
COPY ./go/go.mod ./go/go.sum ./
RUN go mod download
COPY ./go ./
COPY ./.env ./
EXPOSE 80
CMD ["go", "run", "main.go"]