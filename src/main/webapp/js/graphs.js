function getGraphData(statement) {
    var req = new XMLHttpRequest();
    req.open('GET', statement, false);
    req.send(null);
    console.log(req.responseText);
    return req.responseText;
}
function onMonthChange(month, year, graph_type) {
    response = getGraphData('graph_data?year=' + year + '&month=' + month);
    var expenses_json = JSON.parse(response);
    var labels = ['Clothes', 'Cosmetics', 'Hobby and books',
     'Dates and meetings', 'Other'];
    showGraph(labels,
              "Monthly expenses categorized", [
              parseInt(expenses_json.clothes),
              parseInt(expenses_json.cosmetics),
              parseInt(expenses_json.books),
              parseInt(expenses_json.meetings),
              parseInt(expenses_json.other)]);
}

function showGraph(graph_labels, graph_title, graph_data, graph_type = "pie") {
      $('#myChart').remove();
      $('#graph-container').append('<canvas id="myChart" width="500" height="350"></canvas>');

      var ctx = document.getElementById("myChart").getContext('2d');
      ctx.canvas.width = 500;
      ctx.canvas.height = 350;
      config = {
             type: graph_type,
             data: {
                 labels: graph_labels,
                 datasets: [{
                     label: graph_title,
                     data: graph_data,
                     backgroundColor: [
                         'rgba(255, 99, 132, 0.2)',
                         'rgba(54, 162, 235, 0.2)',
                         'rgba(255, 206, 86, 0.2)',
                         'rgba(75, 192, 192, 0.2)',
                         'rgba(153, 102, 255, 0.2)',
                         'rgba(255, 159, 64, 0.2)'
                     ],
                     borderColor: [
                         'rgba(255,99,132,1)',
                         'rgba(54, 162, 235, 1)',
                         'rgba(255, 206, 86, 1)',
                         'rgba(75, 192, 192, 1)',
                         'rgba(153, 102, 255, 1)',
                         'rgba(255, 159, 64, 1)'
                     ],
                     borderWidth: 1
                 }]
             },
             options: {
                 scales: {
                     yAxes: [{
                         ticks: {
                             beginAtZero:true
                         }
                     }]
                 },
                 responsive: false
             }
         };
      myChart = new Chart(ctx, config);
      }

function onGraphTypeChange(graph_type) {
    $('#myChart').remove();
    $('#graph-container').append('<canvas id="myChart" width="500" height="350"></canvas>');

    var ctx = document.getElementById("myChart").getContext('2d');
    var temp = jQuery.extend(true, {}, config);
    temp.type = graph_type;
    myChart = new Chart(ctx, temp);
}

function onExpenseTypeChange(expense_type, month, year) {
    var req = new XMLHttpRequest();
    req.open('GET', 'graph_data?year=' + year + '&month=' + month + '&expense_type=' + expense_type, false);
    req.send(null);
    console.log(req.responseText);
    var expenses_json = JSON.parse(req.responseText);
    var labels = ['Clothes', 'Cosmetics', 'Hobby and books',
     'Dates and meetings', 'Other'];
    showGraph(labels,
              "Monthly expenses categorized", [
              parseInt(expenses_json.clothes),
              parseInt(expenses_json.cosmetics),
              parseInt(expenses_json.books),
              parseInt(expenses_json.meetings),
              parseInt(expenses_json.other)]);
}