# Sound Level Alert System

Create an application that monitors sound levels (measured in decibels) in a given environment. It has three clients: one for generating noise level data, one for processing it, and one for reporting alerts.

## General requirements
- Use Docker containerization
- Use RabbitMQ (or similar) messaging queue


## Component 1: Sound Level Generation Client
- Connection: This client connects to the soundLevelQueue using a point-to-point messaging model.
- Function: Every 2 seconds, it sends random decibel readings (e.g., between 30 dB and 120 dB).

## Component 2: Sound Level Processor
- Message Processing: Exclusively receives messages from the soundLevelQueue
- Evaluation: Determines if the sound level is above the threshold (e.g., 80 dB) considered “too loud.”
- Alert Trigger: After collecting 5 “too loud” readings (either consecutively or within a certain timeframe—depending on your design), the processor sends a message to the soundAlertQueue: “High noise alert: 5 high decibel readings detected.”

## Component 3: Alert Reporting Client
- Consumption: Subscribes to the soundAlertQueue
- Output: Prints to the console, for example: “High noise alert: 5 high decibel readings detected.”

## Create tests to validate:
- Message sending and receiving: Ensure decibel readings flow correctly from the generation client to the processor.
- High noise identification: Check that readings above 80 dB are recognized properly.
- Alert message: Verify that an alert is sent to the soundAlertQueue and that the reporting client prints it to the console.

## Start the system
- just put the following command: `docker-compose up`

### Commands were used during the development
`docker run -it --rm --name rabbitmq -p 5672:5672 -p 15672:15672 rabbitmq:4.1-management`

### Good reference for docker development with Springboot
- [Kickstart Your Spring Boot Application Development](https://www.docker.com/blog/kickstart-your-spring-boot-application-development/)

