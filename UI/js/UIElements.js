function  renderNavBar()
{
  var navDiv = document.createElement('div');
  var inner = '<div id="navigation-breadcrumbs" class="navbar-fixed hide-on-med-and-down"style="position:fixed;z-index:12">'+
       '<nav class="transparent"><div class="nav-wrapper row"><div class="col s4"></div><div class="col s4"><center></center></div>' +
        '<div class="col s4"><ul class="right hide-on-med-and-down"><li>Logged in as </li><li><a href=""><i class="material-icons">settings</i></a></li>'+
        '<li><a href="" ><i class="material-icons">power_settings_new</i></a></li></ul></div></div></nav></div><div class="navbar-fixed" style="z-index:3"><nav class="">'+
        '<div class="nav-wrapper blue darken-2 z-depth-3">&nbsp;&nbsp;&nbsp;<a href="#!" class="brand-logo"><i class="large material-icons">language</i></a></div></nav></div>';
  document.getElementById('navigationBar').innerHTML=inner;
}

function renderTextBox(id,appendId,col,name,labelText,type="text")
{
  var inner = '<div class="input-field col '+col+'">'
  +'<input  id="'+id+'" name="'+name+'" type="'+type+'" class="validate">'
  +'<label for="'+id+'">'+labelText+'</label>'
  +'</div>';
  document.getElementById(appendId).innerHTML=inner;
}

function renderTextBoxWithPic(id,appendId,col,name,labelText,iconName,type="text")
{
  var inner = '<div class="input-field col '+col+'">'
  +'<i class="material-icons prefix">'+iconName+'</i>'
  +'<input  id="'+id+'" name="'+name+'" type="'+type+'" class="validate">'
  +'<label for="'+id+'">'+labelText+'</label>'
  +'</div>';
  document.getElementById(appendId).innerHTML=inner;
}
