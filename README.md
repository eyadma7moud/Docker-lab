# Lab 2: Docker - code files

part3-java-app/   Part 3: App.java + Dockerfile
                  docker build -t java-app:v1 .
                  docker run -d -p 5000:5000 -e STUDENT_NAME="Your Name" java-app:v1
                  open http://localhost:5000

part4-site/       Part 4, Task 4.1: folder for the bind mount
                  docker run -d --name mysite -p 8081:80 -v "$(pwd)/part4-site":/usr/share/nginx/html nginx

part5-calculator/ Part 5: App.java + Dockerfile + docker-compose.yml
                  docker compose up -d --build
                  http://localhost:5000/calc?a=10&b=5&op=add   ->  10.0 add 5.0 = 15.0
                  http://localhost:8080/history.txt           ->  list of all results
                  Task 5.3: add  case "pow": return Math.pow(a, b);  to the switch, then rebuild

Expected results were checked by compiling and running both Java programs.
