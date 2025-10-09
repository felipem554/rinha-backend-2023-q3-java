# rinha-backend-2023-q3-java

Backend for *Challenge Backend 2023 Q3*

Project forked from [https://github.com/hugomarques/rinha-backend-2023-q3-java](https://github.com/hugomarques/rinha-backend-2023-q3-java)

## Implementation

### Version 1

The following adaptations were made:

- Replaced PostgreSQL with **Reactive MongoDB**
- Replaced **Spring MVC** with **Spring WebFlux**
- Used **MongoDB Full Text Search** in the domain class (`@TextIndexed` automatically creates the index)

### Version 2

- Removed **Virtual Threads**
- Removed **Redis** (or any cache)
- Used **Spring Native** (with **GraalVM**)

### Version 3

- Based on **Java 20** (without Virtual Threads)
- Added a field `allFieldsInOne` only for the **FTS (Full Text Search)** index
- Using `writeConcern: UNACKNOWLEDGED` in MongoDB (does not wait for acknowledgment of inserted records)
- Using **local cache** (`HashMap`/`HashSet`)
- Removed **Spring Validation**
- Added **advice for log errors**
- Adjustments in **nginx**
- Adjustments in **CPU weights**

## Requirements

To run the project, you need to have installed:

* **Docker**
* **Gatling** (version used: [3.9.5](https://repo1.maven.org/maven2/io/gatling/highcharts/gatling-charts-highcharts-bundle/3.9.5/))

## How to Run

1. Start the environment (**MongoDB + Redis**) with Docker:

   In the root folder of the project, run:

   ```
   docker compose -f docker-compose-local.yml up
   ```

2. Run the **Gatling** tests:

   ```
   cd stress-test
   ./run-tests.sh
   ```

All of this can also be done inside **IntelliJ**, if you prefer.

After each test, remove the Docker volumes created:

```
docker compose -f docker-compose-local.yml rm -svf
```