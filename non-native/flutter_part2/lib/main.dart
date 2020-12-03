import 'package:flutter/material.dart';

import 'FlightList.dart';

void main() => runApp(MyApp());

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return new MaterialApp(
        title: 'iFly flights List',
        home: new FlightList(

        )
    );
  }
}