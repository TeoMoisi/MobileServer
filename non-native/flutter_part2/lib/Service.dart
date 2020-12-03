
import 'dart:convert';
import 'dart:io';

import 'package:http/http.dart' as http;

import 'Flight.dart';

class FlightService {
  String url = 'http://10.0.2.2:2024';

  getAll() async {
    var response = await http.get('$url/flights', headers: {
      HttpHeaders.contentTypeHeader: 'application/json'
    });
    List <Flight> flights = [];
    if (response != null) {
      if (response.statusCode == 200) {
        var body = json.decode(response.body);
        body['flights'].forEach((flight) => flights.add(Flight.fromMap(flight)));
      }
    }
    return flights;
  }

  createFlight(Flight flight) async {
    var response = await http.post('$url/flights',
        headers: {
          HttpHeaders.contentTypeHeader: 'application/json'
        },
        body: json.encode(flight.toMap())
    );
    if (response != null && response.statusCode == 200) {
      return true;
    }
    return false;
  }

  sync(List <Flight> flights) async {
    List flightJson = [];
    print(flights.map((flight) => flight.id));
    flights.forEach((Flight flight) {
      flightJson.add(flight.toMap());
    });
    var response = await http.post('$url/flights/sync',
        headers: {
          HttpHeaders.contentTypeHeader: 'application/json'
        },
        body: json.encode(flightJson)
    );
    if (response != null && response.statusCode == 200) {
      return true;
    }
    return false;
  }

  deleteFlight(int flightId) async {
    String id = flightId.toString();
    var response = await http.delete('$url/flights/$id');
    if (response != null && response.statusCode == 200) {
      return true;
    }
    return false;
  }

  updateFlight(Flight flight) async {
    String id = flight.id.toString();
    var response = await http.patch('$url/flights/$id',
        headers: {
          HttpHeaders.contentTypeHeader: 'application/json'
        },
        body: json.encode(flight.toMap())
    );
    if (response != null && response.statusCode == 200) {
      return true;
    }
    return false;
  }
}