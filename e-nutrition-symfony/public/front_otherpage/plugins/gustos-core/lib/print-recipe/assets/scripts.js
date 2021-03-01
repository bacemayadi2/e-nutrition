;(function( $ ) {
	
	"use strict";

$(document).ready(function(){

	function cl( _var ){
		console.log( _var );
	}

	function minmax_allowed_size(input, size){
		if( size < 10 ) { // min value is 10
			size = 10;
			$(input).val(size);
		}
		if( size > 40 ) { // max value is 40
			size = 40;
			$(input).val(size);
		}
	}

	var body_font_size = $('body').css('font-size');
	$('.current-font-size').val( parseInt(body_font_size, 10) );

	$('.text-plus, .text-minus').on('click', function(){
		var size = $('body').css('font-size'),
		    body_font_size;

		if( $(this).hasClass('text-minus') && parseInt(size, 10) > 10 ){
			body_font_size = parseInt(size, 10) - 1;
			$('body').css('font-size', body_font_size);
			$('.current-font-size').val( body_font_size );
		}
		else if( $(this).hasClass('text-plus') && parseInt(size, 10) < 40  ){
			body_font_size = parseInt(size, 10) + 1;
			$('body').css('font-size', body_font_size);
			$('.current-font-size').val( body_font_size );
		}

	});

	$(".current-font-size").bind("input", function() {
		var size = parseInt( $(this).val(), 10);
		minmax_allowed_size(this, size);
		$('body').css('font-size', size);
	});

	// $('.content-images').on('click', function(){
	// 	var _t = $(this);
	// 	var _c = $('.content');

	// 	if( _t.hasClass('show') || _t.hasClass('some') ){
	// 		_t.removeClass('some').removeClass('show');
	// 		_c.addClass('no-images');
	// 		$('img').removeClass('hide-img');
	// 	}
	// 	else{
	// 		_t.addClass('show');
	// 		_c.removeClass('no-images');
	// 		$('img').removeClass('hide-img');
	// 	}
	// });

	var element_deleted_index = 1;
	$('.printable-content').children().addClass('child-elem');

	$('.printable-content ').on('click', '.child-elem', function(){
		$(this).addClass('hide-elem');
		$('.remove-undo-all').show();
		$(this).attr('data-deleted', element_deleted_index);
		element_deleted_index++;
	});

	$('.remove-undo-all').on('click', function(){
		$(this).hide();
		$('.printable-content ').find('.child-elem').removeClass('hide-elem');
		$('.printable-content ').find('.child-elem').removeAttr('data-deleted');
		element_deleted_index = 1;
	});

	$('.printable-content .child-elem').qtip({
		content: 'Click to remove',
		position: {
			my: 'bottom center',
			at: 'top center',
		},
		style: {
			classes: 'qtip-tipsy qtip-shadow',
		},
		hide: {
			event: 'click mouseleave'
		}
	});


});
})(jQuery);