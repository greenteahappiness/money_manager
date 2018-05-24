    var myChart;
    var config;

    $( ".column" ).sortable({
      connectWith: ".column",
      handle: ".portlet-header",
      cancel: ".portlet-toggle",
      start: function (event, ui) {
        ui.item.addClass('tilt');
      },
      stop: function (event, ui) {
        ui.item.removeClass('tilt');
      }
    });

    $( ".portlet" )
      .addClass( "ui-widget ui-widget-content ui-helper-clearfix ui-corner-all" )
      .find( ".portlet-header" )
        .addClass( "ui-widget-header ui-corner-all" )
        .prepend( "<span class='ui-icon ui-icon-minusthick portlet-toggle'></span>");

    function changeTheme(newTheme)
    {
        if (newTheme == 'pastel-pink' && !document.getElementById("theme").href.includes('pastel_pink')) {
            document.getElementById("theme").href = document.getElementById("theme").href.replace('/css/', '/css/pastel_pink/');
            document.cookie = "theme=" + newTheme;
        } else if (newTheme == 'default-dark' && document.getElementById("theme").href.includes('pastel_pink')) {
            document.getElementById("theme").href = document.getElementById("theme").href.replace('/css/pastel_pink/', '/css/');
            document.cookie = "theme=" + newTheme;
        }
        console.log(document.getElementById("theme").href)
    }

    function checkCookieSettings()
    {
        var cookies = document.cookie;
        if (cookies.includes("default-dark")) {
            changeTheme("default-dark");
        } else if (cookies.includes("pastel-pink")) {
            changeTheme("pastel-pink");
        }
    }

    window.addEventListener("load", checkCookieSettings);

    function onMonthChange(msg, graph_type) {
        console.log(msg);
        var req = new XMLHttpRequest();
        req.open('GET', 'graph_data?year=2018&month=' + msg, false);
        req.send(null);
        console.log(req.responseText);
        var expenses_json = JSON.parse(req.responseText);
        var labels = ['Clothes', 'Cosmetics', 'Hobby and books', 'Other'];
        showGraph(labels,
                  "Monthly expenses categorized", [
                  parseInt(expenses_json.clothes),
                  parseInt(expenses_json.cosmetics),
                  parseInt(expenses_json.books),
                  parseInt(expenses_json.other)]);
    }

    function showGraph(graph_labels, graph_title, graph_data) {
          $('#myChart').remove();
          $('#graph-container').append('<canvas id="myChart" width="200" height="200"></canvas>');

          var ctx = document.getElementById("myChart").getContext('2d');
          ctx.canvas.width = 300;
          ctx.canvas.height = 300;
          config = {
                 type: 'bar',
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
        $('#graph-container').append('<canvas id="myChart" width="300" height="300"></canvas>');

        var ctx = document.getElementById("myChart").getContext('2d');
        var temp = jQuery.extend(true, {}, config);
        temp.type = graph_type;
        myChart = new Chart(ctx, temp);
    }