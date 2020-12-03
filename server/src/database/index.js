const pg = require("pg");

module.exports = class FlightDatabase {
    constructor() {
        this.pool = new pg.Pool({
            user: "postgres",
            host: "localhost",
            database: "iFly",
            password: "etiopia.010499?",
            port: 5000
        })
    }

    async getAll() {
        const result = await this.pool.query("SELECT * FROM flights")
        return result.rows
    }

    async getFlightById(id) {
        const result = await this.pool.query("SELECT * FROM flights WHERE id = $1", [id])
        return result.rows[0]
    }

    async insert(flight) {
        const result = await this.pool.query(
            "INSERT INTO flights(datetime, departure, arrival, code, details) VALUES ($1, $2, $3, $4, $5)",
            [flight.datetime, flight.departure, flight.arrival, flight.code, flight.details]
        )
        return result.rowCount
    }

    async delete(id) {
        const result = await this.pool.query("DELETE FROM flights WHERE id = $1", [id])
        return result.rowCount
    }

    async update(newFlight) {
        const result = await this.pool.query(
            "UPDATE flights SET datetime = $2, departure = $3, arrival = $4, code = $5, details = $6 WHERE id = $1",
            [newFlight.id, newFlight.datetime, newFlight.departure, newFlight.arrival, newFlight.code, newFlight.details]
        )
        return result.rowCount
    }
}
