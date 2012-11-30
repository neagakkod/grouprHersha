// JS MAD CONFUSING
// BEST TO LOOK AT THROUGH FIREBUG


// tooltips minified
eval(function(p,a,c,k,e,r){e=function(c){return(c<a?'':e(parseInt(c/a)))+((c=c%a)>35?String.fromCharCode(c+29):c.toString(36))};if(!''.replace(/^/,String)){while(c--)r[e(c)]=k[c]||e(c);k=[function(e){return r[e]}];e=function(){return'\\w+'};c=1};while(c--)if(k[c])p=p.replace(new RegExp('\\b'+e(c)+'\\b','g'),k[c]);return p}('(5($){$.i.3=5(g){g=$.y({},$.i.3.L,g);l 1.10(5(){9 f=$.i.3.M(1,g);$(1).11(5(){$.k(1,\'z.3\',12);9 a=$.k(1,\'A.3\');j(!a){a=$(\'<B N="3"><B N="3-O"/></B>\');a.h({13:\'14\',15:16});$.k(1,\'A.3\',a)}j($(1).m(\'4\')||q($(1).m(\'C-4\'))!=\'P\'){$(1).m(\'C-4\',$(1).m(\'4\')||\'\').17(\'4\')}9 b;j(q f.4==\'P\'){b=$(1).m(f.4==\'4\'?\'C-4\':f.4)}D j(q f.4==\'5\'){b=f.4.Q(1)}a.18(\'.3-O\')[f.E?\'E\':\'19\'](b||f.R);9 c=$.y({},$(1).F(),{o:1.S,p:1.T});a.1a(0).1b=\'3\';a.G().h({6:0,7:0,H:\'1c\',U:\'V\'}).1d(I.1e);9 d=a[0].S,r=a[0].T;9 e=(q f.t==\'5\')?f.t.Q(1):f.t;1f(e.1g(0)){u\'n\':a.h({6:c.6+c.p,7:c.7+c.o/2-d/2}).v(\'3-1h\');x;u\'s\':a.h({6:c.6-r,7:c.7+c.o/2-d/2}).v(\'3-1i\');x;u\'e\':a.h({6:c.6+c.p/2-r/2,7:c.7-d}).v(\'3-1j\');x;u\'w\':a.h({6:c.6+c.p/2-r/2,7:c.7+c.o}).v(\'3-1k\');x}j(f.J){a.h({W:0,U:\'V\',H:\'X\'}).1l({W:0.8})}D{a.h({H:\'X\'})}},5(){$.k(1,\'z.3\',K);9 b=1;1m(5(){j($.k(1,\'z.3\'))l;9 a=$.k(b,\'A.3\');j(f.J){a.1n().1o(5(){$(1).G()})}D{a.G()}},1p)})})};$.i.3.M=5(a,b){l $.Y?$.y({},b,$(a).Y()):b};$.i.3.L={J:K,R:\'\',t:\'n\',E:K,4:\'4\'};$.i.3.1q=5(){l $(1).F().6>($(I).1r()+$(Z).p()/2)?\'s\':\'n\'};$.i.3.1s=5(){l $(1).F().7>($(I).1t()+$(Z).o()/2)?\'e\':\'w\'}})(1u);',62,93,'|this||tipsy|title|function|top|left||var||||||||css|fn|if|data|return|attr||width|height|typeof|actualHeight||gravity|case|addClass||break|extend|cancel|active|div|original|else|html|offset|remove|visibility|document|fade|false|defaults|elementOptions|class|inner|string|call|fallback|offsetWidth|offsetHeight|display|block|opacity|visible|metadata|window|each|hover|true|position|absolute|zIndex|100000|removeAttr|find|text|get|className|hidden|appendTo|body|switch|charAt|north|south|east|west|animate|setTimeout|stop|fadeOut|100|autoNS|scrollTop|autoWE|scrollLeft|jQuery'.split('|'),0,{}));

$(function(){
	// appply tooltips to all things with class TG
	$('.TG').tipsy();

	/**
	 * LOGIN
	 */
	$('#loginForm').submit(function(e){
		$('#leftRip, #rightRip').fadeIn(function(){
			$('#loginLeft').animate({"margin-left": "-=2000px"},2000, function(){ $(this).remove();} );
			$('#loginRight').animate({"margin-left": "+=2000px"},2000, function(){ $(this).remove();} );
		});
	});


	/**
	 * NON MEMBER VIEW
	 */
	var profileCheck = false;

	$('#browseGroups, #browseInvites').live("click", function(e){
		e.preventDefault();
		// FOR GROUPS
		if ( $(this).attr('id') == "browseGroups") {
			if ( $('#inviteView').is(':visible') ) {
				$('#inviteView').fadeOut(function(){
					$('#groupView').fadeIn();
					closeAllInvites();
					browseAllGroups();
				});
			} else if ($('#groupView').is(':visible')) {
				$('#groupView').fadeOut(function(){
					$('#content').slideUp(function(){
						closeAllGroups();
					});
				});
			} else {
				$('#groupView').show();
				$('#content').slideDown();
				browseAllGroups();
			}
		// FOR INVITES
		} else {
			if ( $('#groupView').is(':visible') ) {
				$('#groupView').fadeOut(function(){
					$('#inviteView').fadeIn();
					closeAllGroups();
					browseAllInvites();
				});
			} else if ( $('#inviteView').is(':visible') ) {
				$('#inviteView').fadeOut(function(){
					$('#content').slideUp(function(){
						closeAllInvites();
					});
				});
			} else {
				$('#inviteView').show();
				$('#content').slideDown();
				browseAllInvites();
			}
		}
	});
	// Code duplication, needs cleaning
	function browseAllGroups( page ){
		browseTable('/groupsoen3872011k/servlet/app/BrowseGroups?page=1', "#GroupViewFiller", "pageOfGroups", "BrowseGroups?page=", page);
	}
	function closeAllGroups(){
		$("#pageOfGroups").replaceWith("<div id='GroupViewFiller'><img src='http://i.imgur.com/5wsH3.gif' width=30 height=30 alt='LOADING..' /><span>LOADING...</span></div>");
	}
	function browseAllInvites( page ){
		browseTable('/groupsoen3872011k/servlet/app/BrowseInvites?page=1', "#InviteViewFiller", "pageOfInvites", "BrowseInvites?page=", page);
	}
	function closeAllInvites(){
		$("#pageOfInvites").replaceWith("<div id='InviteViewFiller'><img src='http://i.imgur.com/5wsH3.gif' width=30 height=30 alt='LOADING..' /><span>LOADING...</span></div>");
	}


	/**
	 * MEMBER VIEW
	 */
	$('#browseMyGroup, #leaveGroup').live("click", function(e){
		e.preventDefault();
		if ( $(this).attr('id') == "browseMyGroup") {
			if ( $('#leaveGroupView').is(':visible') ) {
				$('#leaveGroupView').fadeOut(function(){
					$('#myGroupView').fadeIn();
					closeAllRequests();
					browseAllMembers();
				});
			} else if ($('#myGroupView').is(':visible')) {
				$('#myGroupView').fadeOut();
				$('#content').slideUp(function(){
					closeAllMembers();
				});
			} else {
				$('#myGroupView').show();
				$('#content').slideDown();
				browseAllMembers();
			}
		} else {
			if ( $('#myGroupView').is(':visible') ) {
				$('#myGroupView').fadeOut(function(){
					$('#leaveGroupView').fadeIn();
					closeAllMembers();
					browseAllRequests();
				});
			} else if ( $('#leaveGroupView').is(':visible') ) {
				$('#leaveGroupView').fadeOut();
					$('#content').slideUp(function(){
						closeAllRequests();
					});
			} else {
				$('#leaveGroupView').show();
				$('#content').slideDown();
				browseAllRequests();
			}
		}
	});
	function browseAllMembers( page ){
		browseTable('/groupsoen3872011k/servlet/app/ViewGroup?page=1', "#memberListFiller", "pageOfMembers", "ViewGroup?page=", page);
	}
	function closeAllMembers(){
		$("#pageOfMembers").replaceWith("<div id='memberListFiller'><img src='http://i.imgur.com/5wsH3.gif' width=30 height=30 alt='LOADING..' /><span>LOADING...</span></div>");
	}
	function browseAllRequests( page ){
		browseTable('/groupsoen3872011k/servlet/app/BrowseRequests?page=1', "#groupRequestFiller", "pageOfRequests", "BrowseRequests?page=", page);
	}
	function closeAllRequests(){
		$("#pageOfRequests").replaceWith("<div id='groupRequestFiller'><img src='http://i.imgur.com/5wsH3.gif' width=30 height=30 alt='LOADING..' /><span>LOADING...</span></div>");
	}

	// cleanup function
	function browseTable(url, oldDiv, newDiv, otherURL, page){
		page = page || 1;
		$('.pagination').remove();
		$.getJSON(url, function(data) {
			insertTableFromJson(data, oldDiv, newDiv, otherURL);
		});
	}


	/**
	 * PROFILE VIEW
	 */
	$('#viewProfile, #closeProfileView').live("click", function(e){
		e.preventDefault();
		if(profileCheck){
			$('#profileView').slideUp();
			$('#gen').slideDown();
			profileCheck=false;
		} else {
			$('#gen').slideUp();
			$('#profileView').slideDown();
			profileCheck=true;
		}
	});

	/**
	 * OVERLAY VIEW
	 */
	$('#createGroup, #sendInviteBtn, #editGroupBtn').live("click", function(e){
		e.preventDefault();

		if ( $(this).attr('id') == "sendInviteBtn" ){
			$('#editGroupInfo').hide();
			$('#sendInvite').show();
		}
		if ( $(this).attr('id') == "editGroupBtn" ) {
			$('#sendInvite').hide();
			$('#editGroupInfo').show();
		}
		$('#overlay').show(function(){
			$('#closeOverlay').click(function() { $('#overlay').hide(); });
			//$('#overlayBox').click(function(){ return false; });
			//$(document).one('click', function() { $('#overlay').hide(); });
		});
	});


	/**
	 * TABLE PRETTYFYING
	 */
	$("table tr:nth-child(odd)").addClass("odd-row");
	/* For cell text alignment */
	$("table td:first-child, table th:first-child").addClass("first");
	/* For removing the last border */
	$("table td:last-child, table th:last-child").addClass("last");

	/**
	 * "JSON 2 TABLE"-IFIER
	 */
	// Object.keys JS SHIV hax (thx underscore.js)
	// http://kangax.github.com/es5-compat-table/
    var oKeys = Object.keys || function(obj) {
    	if (obj !== Object(obj)) {
    		//throw new TypeError('Invalid object');
    		return 0; // no invites
    	}
    	var keys = [];
    	for (var key in obj)
    		if (hasOwnProperty.call(obj, key))
    			keys[keys.length] = key;
    	return keys;
    };

	function insertTableFromJson(json, oldID, newID, paginationURL){
		// browser fix
		if (json[1] === undefined){
			 $(oldID).replaceWith("<p id='"+newID+"'>There are none!</p>")
             return;
		}
		console.log(json[1]);
		var size = oKeys(json[1]).length;
		var pages = json[0].total;
		// browser fix 2
		if(size === undefined || size <= 0){
			$(oldID).replaceWith("<p id='"+newID+"'>Empty :(</p>");
			return;
		}else{
			var pagination = [];
			pagination.push("<div class='pagination'><span>Pages:</span>");
			for (var i=1; i<=pages; i++){
				pagination.push("<a class='page' href='/groupsoen3872011k/servlet/app/" + paginationURL + i +"'>" + i + "</a>");
			}
			pagination.push("</div>");
			$(oldID).replaceWith("<table id='"+newID+"'><thead id='"+newID+"th'> </thead><tbody id='"+newID+"td'> </tbody></table>" + pagination.join(" "));
			pagination.length = 0;
		}
		// Insert Table Headers
		var header = $('#'+newID+' thead');
	    for(var i=0; i < size; i++){
		    header.append('<th>'+oKeys(json[1])[i]+'</th>');
	    }

		// Insert Table Data
		var body = $('#' + newID + ' tbody');
		var skip = true;
		$.each(json, function(i, group) {
			if (skip){
				skip=false;
			} else {
				body.append('<tr />');
				$.each(group, function(property, value) {
					$('#' + newID + ' tr:last').append('<td>'+value+'</td>');
				});
			}
		});
    }


	// Ajax kick people
	$('.kick').live("click", function(e){
		e.preventDefault();
		var placeholder = $(this);
		var req = placeholder.attr('href');
		placeholder.html("<img src='http://i.imgur.com/5wsH3.gif' width=30 height=30 alt='LOADING..' />");

		// do ajax request to url
		$.get(req, function(data){
			placeholder.html("DONE!");
			location.href = "/groupsoen3872011k/servlet/app/home";
		});
	});

	// Ajax accept people
	$('.joinRequest').live("click", function(e){
		e.preventDefault();
		var placeholder = $(this);
		var req = placeholder.attr('href');
		placeholder.html("<img src='http://i.imgur.com/5wsH3.gif' width=30 height=30 alt='LOADING..' />");

		// do ajax request to url
		$.get(req, function(data){
			placeholder.html(data);
		});
	});

	// Pagination code
	$('.page').live("click", function(e){
		e.preventDefault();
		var id = $(this).parent().parent().attr('id');
		var url = $(this).attr("href");

		if(id == "inviteView")
			loadPage(url, "#pageOfInvites", "pageOfInvites", "BrowseInvites?page=");

		else if(id == "groupView")
			loadPage(url, "#pageOfGroups", "pageOfGroups", "BrowseGroups?page=");

		else if(id == "myGroupView")
			loadPage(url, "#pageOfMembers", "pageOfMembers", "ViewGroup?page=");

		else if(id == "leaveGroupView")
			loadPage(url, "#pageOfRequests", "pageOfRequests", "BrowseRequests?page=");
	});

	function loadPage(url, oldDiv, newDiv, otherURL){
		$('.pagination').remove();
		$.getJSON(url, function(data) {
			$('.pagination').remove();
			insertTableFromJson(data, oldDiv, newDiv, otherURL);
			data=null;
		});
	}
});