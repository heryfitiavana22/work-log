# work-log

This project is a real-time employee attendance tracking system. It collects entry/exit logs from a CSV file, processes them through a data pipeline.

## Project flow

![Project Flow](./flow.png)

## Grafana demo

[table.webm](https://github.com/user-attachments/assets/e4d552e4-61bb-4a69-926d-70e9e33b1b27)


[count-by-type-per-interval-time.webm](https://github.com/user-attachments/assets/28fc1e5e-c942-4f5f-8073-305a6766fdbf)



[count-all.webm](https://github.com/user-attachments/assets/2bd33db5-56ea-4ded-a552-9906751923ef)


## Technology Stack
- `Filebeat` monitors the CSV file and sends new log entries to Redis.

- `Redis` acts as a message broker, triggering events for new data.

- `Quarkus` listens to Redis events, processes the data, and indexes it in Elasticsearch.

- `Elasticsearch` stores and organizes the logs for fast searching and filtering.

- `Grafana` provides a dashboard to visualize attendance insights.
