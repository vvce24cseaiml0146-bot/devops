
FROM selenium/standalone-chrome:latest

USER root

# Install Maven
RUN apt-get update && \
    apt-get install -y maven && \
    apt-get clean && \
    rm -rf /var/lib/apt/lists/*

# Set project working directory
WORKDIR /app

# Copy Selenium project files
COPY . .

# Set correct permissions
RUN chown -R seluser:seluser /app

# Switch to Selenium user
USER seluser

# Run Selenium tests
CMD ["mvn", "clean", "test"]
