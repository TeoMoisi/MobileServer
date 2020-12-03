

class Flight {
  String id;
  String departure;
  String arrival;
  String datetime;
  String code;
  String details;

  Flight(this.id, this.departure, this.arrival, this.datetime, this.code, this.details);


  toMap() {
    var map = Map<String, dynamic>();
    if (id != null) {
      map['id'] = id;
    }

    map['departure'] = departure;
    map['arrival'] = arrival;
    map['datetime'] = datetime;
    map['code'] = code;
    map['details'] = details;
    return map;
  }

  Flight.fromMap(Map<String, dynamic> json):
        id = json["id"].toString(),
        departure = json["departure"],
        arrival = json["arrival"],
        datetime = json["datetime"],
        code = json["code"],
        details = json["details"];
}