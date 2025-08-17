# Stage 1: Build the Angular app
FROM node:18 AS build
WORKDIR /app

# Copy package files and install dependencies
COPY package.json package-lock.json ./
RUN npm install
RUN npm install -g @angular/cli

# Copy the entire frontend app and build it
COPY . .
RUN ng build --configuration=production

# Stage 2: Serve the app using Nginx
FROM nginx:latest

# Copy the build output from the build stage to Nginx's web directory
COPY --from=build /app/dist/frontend-dev/browser /usr/share/nginx/html

# Expose port 80 for Nginx
EXPOSE 80

# Start Nginx
CMD ["nginx", "-g", "daemon off;"]