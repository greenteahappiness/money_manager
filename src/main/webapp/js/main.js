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
