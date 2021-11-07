FROM node:10

EXPOSE 6058

WORKDIR /usr/src/app/

COPY package*.json ./

RUN npm install && npm rebuild node-sass

COPY ./src ./src

COPY ./vue.config.js .

COPY ./babel.config.js .
