$(document).ready(function() {
  //  $('#nv_list').removeAttr('data-toggle');
  $(".button-collapse").sideNav();
$(".dropdown-button").dropdown();
$('#LoadingModal').modal({
      dismissible: false,
      opacity: .7,
      inDuration: 300,
      outDuration: 200,
      startingTop: '0%',
      endingTop: '40%',
    }
  );
$('#ErrorModal').modal({
        dismissible: true,
        opacity: .7,
        inDuration: 300,
        outDuration: 200,
        startingTop: '10%',
        endingTop: '20%',
      }
  );

});
