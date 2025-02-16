function convert_csv_to_dict(csv_headers_row, csv_values_row) {
  var jsonFromCsv = csv_values_row.reduce(function (result, field, index) {
    result[csv_headers_row[index]] = field;
    return result;
  }, {});

  return jsonFromCsv;
}

var headers_fn = (function () {
  var csv_headers_row = null;

  return function (csv_arr) {
    var jsonFromCsv = null;

    if (!csv_headers_row) {
      csv_headers_row = csv_arr;
    } else {
      jsonFromCsv = convert_csv_to_dict(csv_headers_row, csv_arr);
    }
    return jsonFromCsv;
  };
})();

function process(event) {
  var csv_arr = event.Get("decoded_csv_arr");
  var jsonFromCsv = headers_fn(csv_arr);

  if (!jsonFromCsv) {
    event.Cancel();
  }
  event.Put("jsonFromCsv", jsonFromCsv);
}
