# pull the base image
FROM node:14.15.4

# set the working direction
WORKDIR /app

# add `/app/node_modules/.bin` to $PATH
ENV PATH /app/node_modules/.bin:$PATH

# install app dependencies
COPY package.json ./

COPY package-lock.json ./

RUN npm install

ARG REACT_APP_API_BASE_URL
ENV REACT_APP_API_BASE_URL=${REACT_APP_API_BASE_URL}

# add app
COPY . ./

# start app
CMD ["npm", "start"]
