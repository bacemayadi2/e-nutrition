/**
 * Gustos theme scripts
 *
 */
;
(function($) {

    "use strict";

    $(document).ready(function() {

        /* Autohide a notice
	-------------------------*/
        function start_site_notice_timer(duration, display) {
            var timer_span = display.find('.timer'),
                start = Date.now(),
                diff,
                minutes,
                seconds;

            //Create timer span if it does not exists
            if (timer_span.length < 1) {
                display.append(' <span class="timer">0s</span>');
            }

            //Find the timer span again(in case if it has been create just before)
            var timer_span = display.find('.timer');

            //Timer fn for interval
            function timer() {
                // get the number of seconds that have elapsed since
                // start_site_notice_timer() was called
                diff = duration - (((Date.now() - start) / 1000) | 0);

                // does the same job as parseInt truncates the float
                minutes = (diff / 60) | 0;
                seconds = (diff % 60) | 0;

                minutes = minutes < 10 ? "0" + minutes : minutes;
                seconds = seconds < 10 ? "0" + seconds : seconds;

                var the_time = (diff > 59) ? minutes + "m " + seconds + "s" : diff + "s";

                timer_span.text(the_time);

                if (diff <= 0) {
                    // add one second so that the count down starts at the full duration
                    // example 05:00 not 04:59
                    start = Date.now() + 1000;
                }
            };
            // we don't want to wait a full second before the timer starts
            timer();
            setInterval(timer, 1000);

            // Hide notice when timer finished
            setTimeout(function() {
                display.slideUp(300, function() {
                    $(this).remove();
                });
            }, duration * 1000);
        }

        start_site_notice_timer(5, $('#site-notification'));


        /* Slider
	--------------*/
        if (jQuery().lightSlider) {
            $(".entry-recipe-slider").lightSlider({
                gallery: true,
                item: 1,
                loop: true,
                slideMargin: 0,
                thumbItem: 9,
                adaptiveHeight: true,
                onAfterSlide: function(el) {
                    $(el).parents('.single-recipe-photo-cover').addClass('activated');
                },
            });
        }

        /* Rw Sidebar
	------------------------------------------------*/
        $('.rw-sidebar').rw_Sidebar();

        /* Main menus
	------------------------------------------------*/
        $('#the-main-menu').smk_Menu({
            breakpoint: 960,
        });
        $('#the-user-menu').smk_Menu({
            // responsive: false,
            breakpointText: '<i class="the-breakpoint-handle-icon fa fa-user-plus"></i>',
            subMenuMarkText: false,
            breakpoint: 600,
        });

        /* Smk Accordion Init
	------------------------------------------------*/
        if (jQuery().accordionjs) {
            $(".jquery_accordion").accordionjs();
        }

        /* Smk Visual Select Init
	------------------------------------------------*/
        if (jQuery().smk_VisualSelect) {
            $('.visual-select').smk_VisualSelect();
        }

        /* Carousel
	------------------------------------------------*/
        $(".carousel-posts").owlCarousel({
            items: 3,
            paginationSpeed: 300,
            rewindSpeed: 400,
        });
        $('.carousel-posts-nav-prev').on('click', function() {
            $(this).parents('.grid-container').find('.carousel-posts').trigger('owl.prev');
        });
        $('.carousel-posts-nav-next').on('click', function() {
            $(this).parents('.grid-container').find('.carousel-posts').trigger('owl.next');
        });

        /* Home Carousel
	------------------------------------------------*/
        for (var i = 1; i < 11; i++) {
            $(".home-carousel-posts.show-" + i + "-items").owlCarousel({
                items: i,
                paginationSpeed: 300,
                rewindSpeed: 400,
            });
        }

        $('.carousel-posts-nav-prev').on('click', function() {
            $(this).parents('.grid-container').find('.home-carousel-posts').trigger('owl.prev');
        });
        $('.carousel-posts-nav-next').on('click', function() {
            $(this).parents('.grid-container').find('.home-carousel-posts').trigger('owl.next');;
        });

        var big_sliders = $('.big-featured-recipes-slider');

        if (jQuery().lightSlider) {
            $.each(big_sliders, function(i) {
                var t = big_sliders.eq(i);
                var pause = t.data('pause');
                t.lightSlider({
                    item: 1,
                    loop: true,
                    pause: pause,
                    auto: pause > 1,
                    slideMargin: 0,
                    addClass: 'home-large-light-slider',
                    prevHtml: '<i class="fa fa-angle-left"></i>',
                    nextHtml: '<i class="fa fa-angle-right"></i>',
                });
            });
        }

        function is_numeric(n) {
            return !isNaN(parseFloat(n)) && isFinite(n);
        }

        function smk_recipe_calc_servings(servings, mode) {
            var qt = $('#main-ingredients-list li .servings-val');
            var orig_serv_val = $('.recipe-servings-calculator').find('input').data('original-servings-val');

            $(qt).each(function() {
                var orig_val = $(this).data('original-val'),
                    orig_char = $(this).data('original-char'),
                    orig_val_string = orig_val.toString(),
                    has_comma = orig_val_string.indexOf(',') > 0,
                    has_slash = orig_val_string.indexOf('/') > 0,
                    has_po = orig_val_string.indexOf('(') > 0,
                    has_pc = orig_val_string.indexOf(')') > 0;

                // Check if is a valid js number
                if (has_comma === false && has_slash === false && has_po === false && has_pc === false) {
                    if (!is_numeric(orig_val))
                        return;
                }

                if (has_comma) {
                    orig_val = orig_val_string.replace(',', '.');
                }

                // Repeating decimal places. Ex: 24.7(93) = 24.793939393. It's converted to 24.793
                if (has_po && has_pc) {
                    orig_val = new Fraction(orig_val).ceil(5);
                }

                var new_val;

                // First page load or on value change by user
                if ('prepare' === mode) {
                    new_val = orig_val / servings;
                    new_val = new_val.toString();

                    if (has_slash) {
                        new_val = new Fraction(orig_val).div(servings).toString();
                    }
                } else {
                    new_val = new Fraction(orig_val).mul(servings).toString();
                }

                // Prepare fraction with slash
                if (has_slash) {
                    new_val = new Fraction(new_val).toFraction(true).toString();
                }

                if (has_comma) {
                    new_val = new_val.replace('.', ',');
                }

                if ('prepare' === mode) {
                    $(this).data('original-val', new_val);
                } else {
                    if (orig_serv_val === servings) {
                        $(this).text(orig_char);
                    } else {
                        $(this).text((new_val.indexOf('/') > 0 ? new_val : Math.round(parseFloat(new_val) * 1000) / 1000));
                    }
                }
            });
        }

        function smk_recipe_servings_calculator() {
            var calculator = $('.recipe-servings-calculator');

            var def_servings = calculator.find('input').val();

            if (def_servings > 1) {
                smk_recipe_calc_servings(def_servings, 'prepare');
            }

            calculator.find('input').on('change', function() {
                var servings = $(this).val();
                smk_recipe_calc_servings(servings);
            });
        }

        smk_recipe_servings_calculator();

        function smk_recipe_servings_btns() {
            var calculator = $('.recipe-servings-calculator');
            var input = calculator.find('input');

            calculator.find('.calc-btn-minus').on('click', function(event) {
                event.preventDefault();
                var val = input.val();
                if (val > 1) {
                    input.val(new Fraction(val).sub(1)).change();
                }
            });

            calculator.find('.calc-btn-plus').on('click', function(event) {
                event.preventDefault();
                var val = input.val();
                input.val(new Fraction(val).add(1)).change();
            });
        }

        smk_recipe_servings_btns();

        /* Generate random string
	------------------------------------------------*/
        var smk_theme_generate_string = function(_nr, _underscore) {

            var possible_letters = "abcdefghijklmnopqrstuvwxyz",
                possible_nr = "0123456789",
                possible_all = "abcdefghijklmnopqrstuvwxyz0123456789",
                _new_nr, text = "",
                text1 = "",
                text2 = "",
                text3 = "";

            if (_nr < 4) _nr = 4;

            if (_underscore) {
                _new_nr = parseInt(_nr, 10) - 2;
                for (var i = 0; i < 2; i++) {
                    text1 += possible_letters.charAt(Math.floor(Math.random() * possible_letters.length));
                }
                if (_new_nr > 3) {
                    _new_nr = _new_nr - 3;
                    for (var i = 0; i < _new_nr; i++) {
                        text2 += possible_all.charAt(Math.floor(Math.random() * possible_all.length));
                    }
                    for (var i = 0; i < 3; i++) {
                        text3 += possible_nr.charAt(Math.floor(Math.random() * possible_nr.length));
                    }
                } else {
                    for (var i = 0; i < _new_nr; i++) {
                        text2 += possible_all.charAt(Math.floor(Math.random() * possible_all.length));
                    }
                }

                text = text1 + '_' + text2 + text3;
            } else {
                for (var i = 0; i < _nr; i++) {
                    text += possible_all.charAt(Math.floor(Math.random() * possible_all.length));
                }
            }


            return text;
        }

        /* zTip Init
	------------------------------------------------*/
        function smk_theme_tooltip() {

            $('a, span, div, .smk_theme_tooltip').zTip();

            $('.top-user-menu-li a').zTip({
                position: 'bottom',
            });

            $('.post-author-tooltip').zTip({
                theme: 'white',
                source: function(elem) {
                    return elem.next('.post-author-tooltip-block').html();
                },
            });
            $('.comment-form-question').zTip({
                source: function(elem) {
                    return elem.next('.form-allowed-tags').html();
                },
            });
        }

        smk_theme_tooltip();

        /* Main menu sticky
	------------------------------------------------*/
        $(window).scroll(function() {
            if ($(this).scrollTop() > 200) {
                $('.rw-header.mod-fixed-header').addClass("header-fixed");
            } else {
                $('.rw-header.mod-fixed-header').removeClass("header-fixed");
            }
            if ($(this).scrollTop() > 300) {
                $('.rw-header.mod-fixed-header').addClass("header-fixed-show");
            } else {
                $('.rw-header.mod-fixed-header').removeClass("header-fixed-show");
            }
        });

        /* Tag index filter
	------------------------------------------------*/
        function smk_theme_keyword_index_filter(elem) {
            var value = $(elem).val().toLowerCase(),
                headings = $('.keywords-index-list h4');

            // Hide or show headings
            value.length > 0 ? headings.hide() : headings.show();

            // Hide or show keywords
            $(".keywords-index-list li").each(function() {
                $(this).text().toLowerCase().search(value) > -1 ? $(this).removeClass('hide-keyword') : $(this).addClass('hide-keyword');
            });
        }

        $('.keywords-filter-block .filter-keywords').on('keyup', function() {
            smk_theme_keyword_index_filter(this);
        });

        $('.keywords-filter-block .filter-controls').on('click', '.control', function() {
            $(this).parent().children('.control').removeClass('active');
            $(this).addClass('active');
            if ($(this).hasClass('block')) {
                $('.keywords-index-list').addClass('display-block');
            } else {
                $('.keywords-index-list').removeClass('display-block');
            }
        });

        /* Mark recipe ingredient on single recipe page
	------------------------------------------------*/
        $('.single-recipe-ingredients li').on('click', function() {
            $(this).toggleClass('active');
        });

        /* Submission ingredients manage
	------------------------------------------------*/
        if (jQuery().accordionjs) {
            $('.submission-form-repeatable').accordionjs({
                showIcon: false,
                closeAble: true,
            });
        }

        // Live update title and label on typing
        function smk_theme_submission_form_livetext(field, section) {
            $('.submission-form-repeatable').on('keyup change', field, function() {
                var thisval = $(this).val();
                $(this).parents('.acc_section').find(section).text(thisval);
            });
        }

        smk_theme_submission_form_livetext('.this-section-title-field', '.section-title');
        smk_theme_submission_form_livetext('.this-section-label-field', '.section-label');
        smk_theme_submission_form_livetext('.this-section-measure-field', '.section-measure');

        // Delete section
        $('.submission-form-repeatable').on('click', '.delete-section', function(event) {
            event.preventDefault();
            var lists = $(this).parents('.submission-form-repeatable').children();
            if (lists.length > 2) {
                $(this).parents('.acc_section').slideUp(150, false, function() {
                    $(this).remove();
                });
            }
        });

        // Add new section
        $('.submission-form-repeatable-add-new').on('click', function() {
            var array_key = smk_theme_generate_string(5, false),
                the_ul = $(this).prev('.submission-form-repeatable'),
                cloned = $(the_ul).find('.sfa-noindex').clone();

            $(the_ul).children().removeClass('acc_active');
            $(the_ul).find('.acc_content').slideUp(150);
            $(the_ul).append(cloned.hide().addClass('acc_active'));
            cloned.children('.acc_content').show();
            cloned.slideDown(150);
            cloned.removeClass('sfa-noindex');
            cloned.find('input, select').each(function() {
                $(this).attr('name', $(this).attr('name').replace('__noindex__', array_key));
            });
            cloned.find('input').eq(0).focus();
        });

        /* Repeatable inputs
	-------------------------*/
        // Delete section
        $('.repeatable-inputs-block').on('click', '.delete-repeatable-input', function(event) {
            event.preventDefault();
            var inputs = $(this).parents('.repeatable-inputs-block ').children('.repeatable-input');

            if (inputs.length > 1) {
                $(this).parent('.repeatable-input').slideUp(200, function() {
                    $(this).remove();
                });
            }
        });

        // Add new section
        $('.repeatable-input-new').on('click', function() {
            var prev_input = $(this).prev('.repeatable-input'),
                cloned = $(prev_input).clone();

            $(this).before(cloned.hide());

            var the_input = cloned.children('input');
            the_input.val('');
            cloned.slideDown(150);

            the_input.focus();
        });

        /* Profile user tabs
	------------------------------------------------*/
        function smk_theme_profile_user_tabs() {
            $('.profile-mini-menu li:not(.link) a').on('click', function(event) {
                event.preventDefault();
                var theid = $(this).attr('href');

                // The menu
                $('.profile-mini-menu li:not(.link)').removeClass('active');
                $(this).parent().addClass('active');

                //The tabs
                $('.profile-user-tab').removeClass('active');
                $(theid).addClass('active');
            });
        }

        smk_theme_profile_user_tabs();

        /* Recipes info
	------------------------------------------------*/
        $('.recipe .recipe-info-pointer').on({
            'mouseenter': function() {
                $(this).next().addClass('active');
                $(this).prev().addClass('inactive');
            },
            'mouseleave': function() {
                $(this).prev().removeClass('inactive');
                $(this).next().removeClass('active');
            },
        });

        /* Jump to the top
	------------------------------------------------*/
        function smk_theme_jump_to_it(_selector, _speed) {

            _speed = parseInt(_speed, 10) === _speed ? _speed : 300;

            $(_selector).on('click', function(event) {
                event.preventDefault();
                var url = $(this).attr('href'); //cache the url.

                // Animate the jump
                $("html, body").animate({
                    scrollTop: parseInt($(url).offset().top) - 50,
                }, _speed);

            });
        }

        smk_theme_jump_to_it('.footer-to-top', 500);

        /* Add CSS class to comment form button
	------------------------------------------------*/
        $('.comment-respond input[type=submit]').addClass('button primary');


        /* AJAX
	------------------------------------------------*/

        function smk_theme_follow_user() {
            $('.follow-user').on('click', function(event) {
                event.preventDefault();
                var btn = $(this),
                    user_id = btn.data('id');

                if (btn.hasClass('not-allowed')) return true; // If this does not require ajax, because user can't follow

                btn.addClass('loading');
                $.ajax({
                    type: "POST",
                    url: themeAjax.ajax_url,
                    data: {
                        "action": "follow_user",
                        "usertofollow": user_id,
                    },

                    success: function(response) {
                        console.log(response);
                        btn.removeClass('loading');
                        if (response == 'followed') {
                            btn.removeClass('primary').addClass('active current green');
                            btn.find('.text').text(themeAjax.following);
                            btn.find('.fa').removeClass('fa-plus').addClass('fa-check');
                        } else if (response == 'unfollowed') {
                            btn.removeClass('green active current').addClass('primary');
                            btn.find('.text').text(themeAjax.follow);
                            btn.find('.fa').removeClass('fa-check').addClass('fa-plus');
                        }
                    },
                    complete: function(jqXHR, textStatus) {
                        // console.log(jqXHR);
                        // console.log(textStatus);
                    },
                });

            });
        }

        smk_theme_follow_user();

        function smk_theme_send_private_message_delete() {
            $(".private-messages-single").on("click", '.msg-delete', function(event) {
                // event.preventDefault();
                var _message_id = $(this).data('id'),
                    _post_id = $(this).data('post'),
                    _msg = $(this).parents('.single-message');
                console.log(this);
                console.log(_message_id);
                console.log(_post_id);
                console.log(_msg);
                if (_message_id && _post_id) {
                    _msg.css('opacity', 0.5);
                    jQuery.ajax({
                        type: "POST",
                        url: themeAjax.ajax_url,
                        data: {
                            'action': 'smk_private_messages_delete_msg_ajax_handler',
                            'message_id': _message_id,
                            'post_id': _post_id,
                        },
                        success: function(response) {
                            if (response == -1 || response == 0) { // If ajax error
                                _msg.css('opacity', '');
                                return;
                            }
                            $('.private-messages-single').html(response);
                            smk_theme_tooltip();
                        },
                        // complete: function(){}
                    }); //ajax
                }
            });
        }

        smk_theme_send_private_message_delete();

        function smk_theme_send_private_message() {
            $('.private-messaging-form .response-message').on('click', function() {
                $(this).slideUp(150);
            });

            $(document).on("keydown", ".private-messaging-form textarea", function(e) {
                if ((e.keyCode == 10 || e.keyCode == 13) && e.ctrlKey) {
                    $(".private-messaging-form").submit();
                }
            });

            $(".private-messaging-form").on("submit", function(event) {
                event.preventDefault();
                var _form = $(this),
                    _response_msg = _form.find('.response-message'),
                    _form_data = _form.serialize(),
                    _btn = _form.find('.private-messaging-button');

                // console.log( _form );
                _response_msg.slideUp(150);
                _btn.addClass('loading');

                jQuery.ajax({
                    type: "POST",
                    url: themeAjax.ajax_url,
                    data: {
                        'action': 'smk_private_messages_form_ajax_handler',
                        'data': {
                            form_data: _form_data
                        },
                    },
                    success: function(response) {
                        if (response == -1 || response == 0) {
                            return; // If ajax error
                        } else if (parseInt(response) == response) {
                            if (100 == response) {
                                _response_msg.html('<div class="notification red">' + themeAjax.msg100 + '</div>').slideDown(150);
                            } else if (101 == response) {
                                _response_msg.html('<div class="notification red">' + themeAjax.msg101 + '</div>').slideDown(150);
                            } else if (102 == response) {
                                _response_msg.html('<div class="notification green">' + themeAjax.msg102 + '</div>').slideDown(150);
                                _form.find('textarea').val(''); // Reset textarea
                            } else if (103 == response) {
                                _response_msg.html('<div class="notification red">' + themeAjax.msg103 + '</div>').slideDown(150);
                            }
                        } else {
                            $('.private-messages-single').html(response);
                            smk_theme_tooltip();
                            _form.find('textarea').val(''); // Reset textarea
                            smk_theme_send_private_message_delete();
                        }
                    },
                    complete: function() {
                        _btn.removeClass('loading');
                    },
                }); //ajax
            });
        }

        smk_theme_send_private_message();

        function smk_theme_message_mark_as_important() {
            $(".pm-mark-as-important").on("click", function(event) {
                event.preventDefault();
                var _this = $(this),
                    _td_row = _this.parents('.td-row'),
                    _post_id = $(this).data('post');

                if (_post_id) {
                    $(_td_row).css('opacity', 0.5);
                    jQuery.ajax({
                        type: "POST",
                        url: themeAjax.ajax_url,
                        data: {
                            'action': 'smk_private_messages_mark_as_important_ajax_handler',
                            'post_id': _post_id,
                        },
                        success: function(response) {
                            $(_td_row).css('opacity', '');
                            if (response == -1 || response == 0) { // If ajax error
                                return;
                            } else if (response == 'marked') {
                                _this.addClass('active').html('<i class="fa fa-check-circle"></i>');
                            } else if (response == 'unmarked') {
                                _this.removeClass('active').html('<i class="fa fa-circle-o"></i>');
                            }
                        },
                        // complete: function(){}
                    }); //ajax
                }
            });
        }

        smk_theme_message_mark_as_important();

        function gustos_pb_items_list_switcher() {
            $(".gustos-pb-items-list-switcher").on("change", function(event) {
                event.preventDefault();
                var _this = $(this),
                    _val = _this.val(),
                    _mode = _this.data('mode'),
                    _settings = _this.data('settings'),
                    _container = _this.closest('.grid-container').find('.gustos-pb-items-list');

                _container.css('opacity', 0.5);

                jQuery.ajax({
                    type: "POST",
                    url: themeAjax.ajax_url,
                    data: {
                        'action': 'gustos_pb_items_list_switcher',
                        'value': _val,
                        'mode': _mode,
                        'settings': _settings,
                    },
                    success: function(response) {
                        $(_container).css('opacity', '');
                        _container.html(response);
                    },
                    // complete: function(){}
                });
            });
        }

        gustos_pb_items_list_switcher();

        /*
	-------------------------------------------------------------------------------
	Refresh PM list
	-------------------------------------------------------------------------------
	*/

        function smk_theme_refresh_private_message() {
            //Check if current page contains a list of message. So we can run the ajax if needed.
            var list = $('.private-messages-single');

            if (list.length > 0) {
                setInterval(function() {
                    jQuery.ajax({
                        type: "POST",
                        url: themeAjax.ajax_url,
                        data: {
                            'action': 'smk_private_messages_refresh_ajax_handler',
                            'discussion_id': list.data('discussion-id'),
                        },
                        success: function(response) {
                            if (!response || response == -1 || response == 0) {
                                return; // If ajax error
                            } else {
                                list.html(response);
                                smk_theme_tooltip();
                                smk_theme_send_private_message_delete();
                            }
                        },
                    });
                }, 5000);
            }
        }

        smk_theme_refresh_private_message();


        /*
	-------------------------------------------------------------------------------
	Auth
	-------------------------------------------------------------------------------
	*/
        function smk_theme_open_login_form() {
            var _form_type = $('.smk-theme-login-block .smk-login-form-type');
            $('.smk-theme-login-block-back-shadow, .smk-theme-login-block').addClass('active');
            $('.smk-theme-login-block input:eq(0)').focus(); // Focus first input on form open
            $(".smk-theme-login-block .fr-reg-y:not(.fr-log-y), .smk-theme-login-block .fr-rec-y").slideUp(150);
            $(".smk-theme-login-block .fr-log-y").slideDown(150);
            $(".smk-theme-login-block-type span").removeClass('active');
            $(".smk-theme-login-block-type span.type-login").addClass('active');
            _form_type.val('log');
        }

        function smk_theme_open_registration_form() {
            var _form_type = $('.smk-theme-login-block .smk-login-form-type');
            $('.smk-theme-login-block-back-shadow, .smk-theme-login-block').addClass('active');
            $('.smk-theme-login-block input:eq(0)').focus(); // Focus first input on form open
            $(".smk-theme-login-block .fr-log-y:not(.fr-reg-y), .smk-theme-login-block .fr-rec-y").slideUp(150);
            $(".smk-theme-login-block .fr-reg-y").slideDown(150);
            $(".smk-theme-login-block-type span").removeClass('active');
            $(".smk-theme-login-block-type span.type-register").addClass('active');
            _form_type.val('reg');
        }

        function login_auth_redirect() {
            var url = themeAjax.login_redirect_url;
            if (url && url !== '') {
                location.assign(url);
                return true;
            }

            location.reload(true);
            return true;
        }

        function reg_auth_redirect() {
            var url = themeAjax.registration_redirect_url;
            if (url && url !== '') {
                location.assign(url);
                return true;
            }

            location.reload(true);
            return true;
        }

        function smk_theme_login_form() {

            // Prepare form on page ready
            $(".smk-theme-login-block .fr-log-y").show();
            $(".smk-theme-login-block .fr-reg-y:not(.fr-log-y), .smk-theme-login-block .fr-rec-y").hide();

            var _form_type = $('.smk-theme-login-block .smk-login-form-type');

            // Show form type
            $(".smk-theme-login-block-type").on("click", 'span', function(event) {
                event.preventDefault();

                if (!$(this).hasClass('active')) {
                    $(".smk-theme-login-block-type span").removeClass('active');
                    $(this).addClass('active');
                    $('.form-message-response').slideUp(150);

                    if ($(this).hasClass('type-login')) {
                        $(".smk-theme-login-block .fr-reg-y:not(.fr-log-y), .smk-theme-login-block .fr-rec-y").slideUp(150);
                        $(".smk-theme-login-block .fr-log-y").slideDown(150);
                        _form_type.val('log');
                    } else if ($(this).hasClass('type-register')) {
                        $(".smk-theme-login-block .fr-log-y:not(.fr-reg-y), .smk-theme-login-block .fr-rec-y").slideUp(150);
                        $(".smk-theme-login-block .fr-reg-y").slideDown(150);
                        _form_type.val('reg');
                    } else if ($(this).hasClass('type-recover')) {
                        $(".smk-theme-login-block .fr-reg-y, .smk-theme-login-block .fr-log-y").slideUp(150);
                        $(".smk-theme-login-block .fr-rec-y").slideDown(150);
                        _form_type.val('rec');
                    }
                }

            });

            // Open login form
            $(".smk-theme-link-to-login").on("click", function(event) {
                event.preventDefault();
                smk_theme_open_login_form();
            });

            // Open registration form
            $(".smk-theme-link-to-register").on("click", function(event) {
                event.preventDefault();
                smk_theme_open_registration_form();

            });

            // Close form
            $(".smk-theme-login-block-close, .smk-theme-login-block-back-shadow").on("click", function(event) {
                event.preventDefault();
                $('.smk-theme-login-block-back-shadow, .smk-theme-login-block').removeClass('active');
                $('.smk-theme-login-block .form-row input').val(''); // Clear inputs
                $('.form-message-response').html(''); // Clear messages
            });

            // Submit form
            $(".smk-theme-login-form").on("submit", function(event) {
                event.preventDefault();
                var _form = $(this),
                    _msg = _form.find('.form-message-response'),
                    _form_data = _form.serialize(),
                    _button = $('.smk-theme-login-form-button');


                _msg.slideUp(150);
                _button.attr('disabled', true).addClass('loading');
                jQuery.ajax({
                    type: "POST",
                    url: themeAjax.ajax_url,
                    data: {
                        'action': 'smk_login_form_ajax_handler',
                        'data': _form_data,
                    },
                    success: function(response) {
                        console.log(response);
                        console.log(themeAjax.registration_redirect_url);
                        // _msg.html(response);
                        if (response == -1 || response == 0) {
                            _msg.html('<div class="notification red">' + themeAjax.login_msg105 + '</div>').slideDown(150);
                            return; // If ajax error
                        } else if (parseInt(response) == response) {
                            if (101 == response) {
                                _msg.html('<div class="notification red">' + themeAjax.login_msg101 + '</div>').slideDown(150);
                            } else if (102 == response) {
                                _msg.html('<div class="notification red">' + themeAjax.login_msg102 + '</div>').slideDown(150);
                            } else if (104 == response) {
                                _msg.html('<div class="notification green">' + themeAjax.login_msg104 + '</div>').slideDown(150);
                                login_auth_redirect();
                            } else if (113 == response) {
                                _msg.html('<div class="notification green">' + themeAjax.login_msg113 + '</div>').slideDown(150);
                                reg_auth_redirect();
                            } else if (116 == response) {
                                _msg.html('<div class="notification green">' + themeAjax.login_msg116 + '</div>').slideDown(150);
                                login_auth_redirect();
                            } else {
                                _msg.html('<div class="notification red">' + themeAjax['login_msg' + response] + '</div>').slideDown(150);
                            }
                        }
                        // It's a plain error message
                        else if (parseInt(response) != response) {
                            _msg.html('<div class="notification red">' + response + '</div>').slideDown(150);
                        }

                        _button.attr('disabled', false).removeClass('loading');

                    },
                    error: function() {
                        _msg.html('<div class="notification red">' + themeAjax.login_msg105 + '</div>').slideDown(150);
                        _button.attr('disabled', false).removeClass('loading');
                    },
                    // complete: function(){}
                }); //ajax
            });
        }

        smk_theme_login_form();

        function getUrlParameter(sParam) {
            var sPageURL = decodeURIComponent(window.location.search.substring(1)),
                sURLVariables = sPageURL.split('&'),
                sParameterName,
                i;

            for (i = 0; i < sURLVariables.length; i++) {
                sParameterName = sURLVariables[i].split('=');

                if (sParameterName[0] === sParam) {
                    return sParameterName[1] === undefined ? true : sParameterName[1];
                }
            }
        }


        function openAuthFormOnUrlParameter() {
            var error = getUrlParameter('error');
            var auth = getUrlParameter('auth');
            if ('not-logged-in' === error || 'login' === auth) {
                smk_theme_open_login_form();
            } else if ('register' === auth) {
                smk_theme_open_registration_form();
            }
        }

        openAuthFormOnUrlParameter();

        function smk_theme_user_settings_form() {
            $("#smk-theme-user-settings-form").on("submit", function(event) {
                event.preventDefault();
                var _this = $(this),
                    _form_data = _this.serialize(),
                    _button = $('#smk-theme-user-settings-submit-button');
                $('.smk-field').removeClass('field-error')
                $('body').find('.smk-field-error').remove();
                _button.attr('disabled', true).addClass('loading');
                jQuery.ajax({
                    type: "POST",
                    url: themeAjax.ajax_url,
                    dataType: "json",
                    data: {
                        'action': 'smk_user_settings_form_ajax_handler',
                        'data': _form_data,
                    },
                    success: function(response) {
                        console.log(response);
                        _button.attr('disabled', false).removeClass('loading');
                        if (jQuery.type(response) == 'object') {
                            if (response.demo_mode_submit) {
                                _this.after('<div class="smk-field-error notification red super-light"><strong>' + themeAjax.demo_mode_submit + '</strong></div>');
                            } else if (response.msg_type == 'error') {
                                $.each(response, function(index, elem) {
                                    if (elem != 'error') {
                                        $('.smk-field-' + index + ', ' + '[data-id="' + index + '"]').addClass('field-error').after('<div class="smk-field-error text-red">' + elem + '</div>');
                                    }
                                });
                                _this.after('<div class="smk-field-error notification red super-light"><strong>' + response.message + '</strong></div>');
                            } else if (response.msg_type == 'success') {
                                _this.after('<div class="smk-field-error notification green super-light"><strong>' + response.message + '</strong></div>');

                                if (response.redirect_url) {
                                    location.assign(response.redirect_url);
                                }

                                window.setTimeout(function() {
                                    $('body').find('.smk-field-error').slideUp(150);
                                }, 3500);
                            }
                        }
                    },
                    // complete: function(){}
                }); //ajax
            });
        }

        smk_theme_user_settings_form();

        /*
	-------------------------------------------------------------------------------
	Submit recipe
	-------------------------------------------------------------------------------
	*/

        function smk_submit_recipe() {
            $("#smk-submit-recipe").on("submit", function(event) {
                event.preventDefault();

                if (typeof(tinymce) !== "undefined") {
                    tinymce.triggerSave();
                }

                $(this).find('input, textarea, select').change();

                var _this = $(this),
                    _form_data = _this.serialize(),
                    _button = $('#smk-submit-recipe-button'),
                    ajaxMsg = themeAjax.submit_recipe;

                _button.attr('disabled', true).addClass('loading');
                _this.find('.smk-field-error').slideUp(150, false, function() {
                    _this.find('[name]').css({
                        'border-color': ''
                    });
                    $(this).remove();
                });
                _this.find('.submission_notice').fadeOut(100, false, function() {
                    $(this).remove();
                });

                jQuery.ajax({
                    type: "POST",
                    url: themeAjax.ajax_url,
                    dataType: "json",
                    data: {
                        'action': 'smk_submit_recipe_ajax_handler',
                        'data': _form_data,
                    },
                    success: function(response) {
                        console.log(response);
                        _button.attr('disabled', false).removeClass('loading');

                        if (response.demo_mode_submit) {
                            _button.after('<span class="submission_notice red" style="display: none;">' + themeAjax.demo_mode_submit + '</span>');
                        } else if (!response.post_id) {
                            $.each(response, function(key, value) {
                                var _field;
                                if ($('[data-field="' + key + '"]').has('.form-row.inline')) {
                                    _field = $('[data-field="' + key + '"] .form-field');
                                } else {
                                    _field = $('[data-field="' + key + '"]');
                                }
                                _field.append('<div class="smk-field-error text-red" style="display: none;">' + value + '</div>');

                                $('[name="' + key + '"]').css({
                                    'border-color': '#EB5858'
                                });
                                _this.find('.smk-field-error').slideDown(150);
                            });
                            _button.after('<span class="submission_notice red" style="display: none;">' + ajaxMsg.recipe_errors + '</span>');
                        } else {
                            if (response.submit_type == 'new') {
                                _button.attr('disabled', true);

                                if (response.post_status == 'pending') {
                                    _button.after('<span class="submission_notice green" style="display: none;">' + ajaxMsg.recipe_pending + ' - <a href="' + response.user_recipes_url + '">' + ajaxMsg.check_recipe_status + '</span>');
                                } else {
                                    _button.after('<span class="submission_notice green" style="display: none;">' + ajaxMsg.recipe_approved + ' - <a href="' + response.post_url + '">' + ajaxMsg.view_your_recipe + '</a></span>');
                                }
                            } else if (response.submit_type == 'updated') {
                                if (response.post_status == 'pending') {
                                    _button.after('<span class="submission_notice green" style="display: none;">' + ajaxMsg.recipe_updated + ' - <a href="' + response.user_recipes_url + '">' + ajaxMsg.check_recipe_status + '</span>');
                                } else {
                                    _button.after('<span class="submission_notice green" style="display: none;">' + ajaxMsg.recipe_updated + ' - <a href="' + response.post_url + '">' + ajaxMsg.view_your_recipe + '</a></span>');
                                }
                            }
                        }
                        _this.find('.submission_notice').fadeIn(100);
                    },
                    // complete: function(){}
                }); //ajax
            });
        }

        smk_submit_recipe();

        /*
        -------------------------------------------------------------------------------
        Multi-select
        -------------------------------------------------------------------------------
        */

        if (typeof SlimSelect === 'function') {
            const selects = document.querySelectorAll('select[multiple]')

            if (selects && selects.length > 0) {
                for (let i = 0; i < selects.length; i++) {
                    new SlimSelect({
                        select: selects[i]
                    })
                }
            }
        }
        /*
        -------------------------------------------------------------------------------
        Manage recipe
        -------------------------------------------------------------------------------
        */
        function smk_manage_recipe() {
            var _manage = $(".smk-manage-recipe");

            _manage.on('click', '.manage-recipe-handler', function(event) {
                event.preventDefault();
                var _this = $(this),
                    _parent = _this.parents(".smk-manage-recipe");

                // First hide all opened
                $(".smk-manage-recipe").not(_parent).removeClass('active');

                _parent.toggleClass('active');
            });

            //Hide/Show when we leave the area
            $(document).mouseup(function(event) {
                if (!_manage.is(event.target) // if the target of the click isn't the container...
                    &&
                    _manage.has(event.target).length === 0 // ... nor a descendant of the container
                ) {
                    _manage.removeClass('active');
                }
            });
        }

        smk_manage_recipe();

        function smk_manage_recipe_remove() {
            $('.smk-remove-recipe').on('click', function(event) {
                event.preventDefault();
                var _this = $(this),
                    _post_id = _this.data('id');

                var confirm_delete = confirm(themeAjax.confirm_recipe_delete);

                if (confirm_delete) {
                    _this.addClass('loading-element');
                    jQuery.ajax({
                        type: "POST",
                        url: themeAjax.ajax_url,
                        dataType: "json",
                        data: {
                            'action': 'smk_remove_recipe_ajax_handler',
                            'post_id': _post_id,
                        },
                        success: function(response) {
                            _this.removeClass('loading-element');
                            _this.parent().find('a').not('.smk-remove-recipe').slideUp(100, false, function() {
                                $(this).remove();
                            });
                            _this.text(response.message);
                            setTimeout(function() {
                                if (response.redirect) {
                                    window.location.assign(response.redirect);
                                }
                            }, 200);
                        },
                    });
                }
            });
        }

        smk_manage_recipe_remove();

        function smk_manage_recipe_approve_publish() {
            $('.smk-approve-unapprove-recipe').on('click', function(event) {
                event.preventDefault();
                var _this = $(this),
                    _post_id = _this.data('id');

                _this.addClass('loading-element');
                jQuery.ajax({
                    type: "POST",
                    url: themeAjax.ajax_url,
                    dataType: "json",
                    data: {
                        'action': 'smk_approve_publish_recipe_ajax_handler',
                        'post_id': _post_id,
                    },
                    success: function(response) {
                        _this.removeClass('loading-element');
                        if (!response.error_message) {
                            if (response.status === 'approved') {
                                _this.removeClass('approve').addClass('unapprove');
                            } else if (response.status === 'unapproved') {
                                _this.addClass('approve').removeClass('unapprove');
                            }
                        }
                        _this.text(response.text);
                    },
                });
            });
        }

        smk_manage_recipe_approve_publish();

        function smk_manage_recipe_make_featured() {
            $('.smk-make-recipe-featured').on('click', function(event) {
                event.preventDefault();
                var _this = $(this),
                    _post_id = _this.data('id');

                _this.addClass('loading-element');
                jQuery.ajax({
                    type: "POST",
                    url: themeAjax.ajax_url,
                    dataType: "json",
                    data: {
                        'action': 'smk_make_recipe_featured_ajax_handler',
                        'post_id': _post_id,
                    },
                    success: function(response) {
                        _this.removeClass('loading-element');
                        if (response.status) {
                            if (response.status == 'featured') {
                                _this.removeClass('not_featured').addClass('featured');
                            } else if (response.status == 'not_featured') {
                                _this.addClass('not_featured').removeClass('featured');
                            }
                            _this.text(response.featured);
                        }
                    },
                });
            });
        }

        smk_manage_recipe_make_featured();

        function smk_recipes_remove_reputation() {
            $('.smk-recipes-remove-reputation').on('click', function(event) {
                event.preventDefault();
                var _this = $(this);

                _this.parents('.reputation-event').css('opacity', 0.5);
                jQuery.ajax({
                    type: "GET",
                    url: themeAjax.ajax_url,
                    data: _this.attr('href').split('?')[1],
                    success: function(response) {
                        // console.log(response);
                        _this.parents('.reputation-event').slideUp('fast');
                    },
                });
            });
        }

        smk_recipes_remove_reputation();

        function smk_recipes_remove_activity() {
            $('.smk-recipes-remove-activity').on('click', function(event) {
                event.preventDefault();
                var _this = $(this);

                _this.parents('.single-notification').css('opacity', 0.5);
                jQuery.ajax({
                    type: "GET",
                    url: themeAjax.ajax_url,
                    data: _this.attr('href').split('?')[1],
                    success: function(response) {
                        // console.log(response);
                        _this.parents('.single-notification').slideUp('fast');
                    },
                });
            });
        }

        smk_recipes_remove_activity();

        $('#smk-user-settings-password').on('keyup', function() {
            var _field_val = $(this).val(),
                _meter_ind = $('#smk-user-settings-password-meter .meter-ind'),
                len_score = 0,
                nr_score = 0,
                alphaL_score = 0,
                alphaU_score = 0,
                special_score = 0,
                percent = 0;

            if (_field_val.length > 0) len_score = 0;
            if (_field_val.length > 3) len_score = 1;
            if (_field_val.length > 7) len_score = 2;
            if (_field_val.length > 9) len_score = 3;
            if (_field_val.length > 11) len_score = 4;

            alphaL_score = _field_val.match(/[a-z]/) ? 1 : 0;
            alphaU_score = _field_val.match(/[A-Z]/) ? 2 : 0;
            special_score = _field_val.match(/.[!@#$%^&*?_~]/) ? 2 : 0;
            nr_score = _field_val.match(/[0-9]/) ? 1 : 0;
            percent = (len_score + alphaL_score + alphaU_score + special_score + nr_score) * 10;

            _meter_ind.stop().animate({
                'left': (percent) + '%'
            }, 500);
        });

        $('#smk-user-settings-password, #smk-user-settings-password-confirm').on('keyup', function() {
            var _pass1 = $('#smk-user-settings-password'),
                _pass2 = $('#smk-user-settings-password-confirm'),
                _pass1_val = $('#smk-user-settings-password').val(),
                _pass2_val = $('#smk-user-settings-password-confirm').val();

            if (_pass2_val === _pass1_val) {
                _pass1.css({
                    'border-color': '#89C245'
                });
                _pass2.css({
                    'border-color': '#89C245'
                });
            } else {
                _pass1.css({
                    'border-color': ''
                });
                _pass2.css({
                    'border-color': '#EB5858'
                });
            }

            if (_pass2_val.length < 1) {
                _pass1.css({
                    'border-color': ''
                });
                _pass2.css({
                    'border-color': ''
                });
            }
        });

        function smk_theme_print_button_animate() {
            $(".recipe-tools").on("click", '.tool.print', function() {
                var _this = $(this);
                _this.addClass('loading-element');
            });
        }

        smk_theme_print_button_animate();

        function smk_theme_add_to_favorites() {
            $(document).on("click", ".smk-mark-button", function(event) {
                event.preventDefault();
                var _this = $(this),
                    _post_id = _this.data('post'),
                    settings = _this.data('settings') ? _this.data('settings') : false,
                    logged = _this.data('l') ? _this.data('l') : false,
                    act = _this.data('act') ? _this.data('act') : false;

                if (parseInt(_post_id) > 0 && settings && logged) {
                    _this.addClass('loading-element');
                    jQuery.ajax({
                        type: "POST",
                        dataType: "json",
                        url: themeAjax.ajax_url,
                        data: {
                            'action': 'smk_mark_post_' + act,
                            'data': {
                                'post': _post_id,
                                'settings': settings,
                            },
                        },
                        success: function(obj) {
                            // console.log( obj );
                            _this.removeClass('loading-element');
                            var _activeClass = (obj.class_active) ? obj.class_active : false;


                            // What is the status?
                            if (_activeClass) {
                                if (obj.js_status == 'added') {
                                    _this.addClass(_activeClass);
                                    var _content_remove = obj.content_remove;
                                    _content_remove = _content_remove.replace(/\\\'/g, "'").replace(/\\\"/g, '"').replace(/\\\\/g, '\\');
                                    _this.html(_content_remove);
                                } else if (obj.js_status == 'removed') {
                                    _this.removeClass(_activeClass);
                                    var _content = obj.content;
                                    _content = _content.replace(/\\\'/g, "'").replace(/\\\"/g, '"').replace(/\\\\/g, '\\');
                                    _this.html(_content);
                                }
                            }

                            // Update the counter text number that is before or after the button
                            if (obj.modify_content == 'next') {
                                _this.next().text(obj.js_total_number_of_fav);
                            }
                            if (obj.modify_content == 'prev') {
                                _this.prev().text(obj.js_total_number_of_fav);
                            }

                            // Change the title
                            _this.attr('title', obj.attributes.title);
                        },
                        // complete: function(){}
                    }); //ajax
                }
            });
        }

        smk_theme_add_to_favorites();

        function smk_theme_comment_rating_stars() {
            $('.comment-form-rating-field i').on('mouseover', function() {
                var _t = $(this);
                var rating = parseInt(_t.data('order'), 10);
                _t.parent().find('i').removeClass('fa-star').addClass('fa-star-o');
                _t.parent().find('i:lt(' + rating + ')').removeClass('fa-star-o').addClass('fa-star');
            });

            $('.comment-form-rating-field i').on('mouseout', function() {
                var _t = $(this);
                var rating = parseInt(_t.parent().find('input').val(), 10);
                _t.parent().find('i').removeClass('fa-star').addClass('fa-star-o');
                _t.parent().find('i:lt(' + rating + ')').removeClass('fa-star-o').addClass('fa-star');
            });

            $('.comment-form-rating-field input').on('change', function() {
                var _t = $(this);
                var rating = parseInt(_t.val(), 10);
                _t.parent().find('i').removeClass('fa-star').addClass('fa-star-o');
                _t.parent().find('i:lt(' + rating + ')').removeClass('fa-star-o').addClass('fa-star');

                var cr = _t.parent().find('.clear-rating');
                if (rating > 0) {
                    cr.fadeIn(150);
                } else {
                    cr.fadeOut(150);
                }
            });

            $('.comment-form-rating-field i').on('click', function() {
                var rating = parseInt($(this).data('order'), 10);
                $(this).parent().find('input').val(rating).change();
            });

            $('.comment-form-rating-field .clear-rating').on('click', function() {
                $(this).parent().find('input').val(0).change();
            });

            $('.comment-reply-link').on('click', function() {
                $('.comment-respond .comment-form-rating-field input').val(0).change();
            });
        }

        smk_theme_comment_rating_stars();

        function smk_members_list_form() {
            $('.smk-members-list-form').on('change', 'input, select', function() {
                $(this).parents('form').submit();
            });
        }

        smk_members_list_form();

        // function smk_recipes_filter_select_make_active(){
        // 	$( ".recipes-filter select" ).each( function( index, elem ) {
        // 		if( $(elem).val() ){
        // 			$(elem).addClass('active');
        // 		}
        // 	});
        // }
        // smk_recipes_filter_select_make_active();

        // function smk_recipes_filter_select(){
        // 	$( ".recipes-filter" ).on( "change", 'select', function( event ) {
        // 		var _t    = $(this),
        // 		    _name = _t.attr('name'),
        // 		    _val  = _t.val(),
        // 		    _url  = window.location,
        // 		    uri   = new Uri( _url );

        // 		$( ".recipes-filter select" ).attr('disabled', true);
        // 		_t.addClass('loading-element');

        // 		// Delete paged arg
        // 		uri.deleteQueryParam('pag');

        // 		var _new = uri.replaceQueryParam(_name, _val);
        // 		window.location.assign( _new );
        // 	});
        // }
        // smk_recipes_filter_select();

        // function smk_recipes_filter_view(){
        // 	$( ".recipes-filter" ).on( "click", '.option', function( event ) {
        // 		var _t    = $(this),
        // 		    _name = _t.data('name'),
        // 		    _val  = _t.data('val'),
        // 		    _url  = window.location,
        // 		    uri   = new Uri( _url );

        // 		if( ! _t.hasClass('active') ){
        // 			$( ".recipes-filter select" ).attr('disabled', true);
        // 			$( ".recipes-filter .option" ).not(this).removeClass('active').addClass('inactive');
        // 			_t.addClass('active');
        // 			var _new = uri.replaceQueryParam(_name, _val);
        // 			window.location.assign( _new );
        // 		}
        // 	});
        // }
        // smk_recipes_filter_view();


        // function smk_comments_ajax_submit(){
        // 	$( "#commentform" ).on( "submit", function( event ) {
        // 		event.preventDefault();
        // 		var _this = $(this),
        // 		    _form_data = _this.serialize(),
        // 		    _button = $(this).find('.form-submit .button');

        // 		_button.attr('disabled', true).addClass('loading');
        // 		console.log(_form_data);
        // 		jQuery.ajax({
        // 			type: "POST",
        // 			url: themeAjax.ajax_url,
        // 			// dataType: "json",
        // 			data: {
        // 				'action': 'smk_comments_ajax_submit',
        // 				'data': _form_data
        // 			},
        // 			success: function(response){
        // 				console.log( response );
        // 				_button.attr('disabled', false).removeClass('loading');
        // 				$( '#all-post-comments' ).html( response );
        // 			},
        // 			// complete: function(){}
        // 		});//ajax
        // 	});
        // }
        // smk_comments_ajax_submit();


        /* Do not jump on top when the link contain only the hash(#) sign
	------------------------------------------------*/
        // $('a[href="#"]').on('click', function(event){
        // 	event.preventDefault();
        // 	return false;
        // });

        /* If the menu has empty links make them less relevant to the user
	------------------------------------------------*/
        // $('.menu ul a[href="#"]').css({
        // 	// 'text-decoration': 'line-through',
        // 	'font-style': 'italic',
        // 	'opacity': '0.8'
        // });

        /* Grid demo
	------------------------------------------------*/
        // function smk_theme_grid_demo(){
        // 	$('.demo-grid-col').each( function(index, elem){
        // 		if( $(this).parent().hasClass('grid') ){
        // 			var tc = $(elem).parent('.grid').attr('class').replace('grid', '');
        // 			$(elem).append(tc);
        // 		}
        // 	} );

        // }
        // smk_theme_grid_demo();

        /* Fake bahavoir
	------------------------------------------------*/
        // function smk_theme_like_favorite(){
        // 	$('.entry-controls .entry-to-favorites, .entry-controls .entry-like').on('click', function(event){
        // 		event.preventDefault();
        // 		var btn = $(this),
        // 			counter = btn.next('.control-tip'),
        // 			count = parseInt( counter.text(), 10 );
        // 		if( btn.hasClass('active') ){
        // 			btn.removeClass('active');
        // 			counter.text( count - 1 );
        // 		}
        // 		else{
        // 			btn.addClass('active');
        // 			counter.text( count + 1 );
        // 		}
        // 		return false;
        // 	});
        // }
        // smk_theme_like_favorite();

        // function smk_theme_recipe_tools(){
        // 	$('.recipe-tools .tool:not(.share):not(.print)').on('click', function(event){
        // 		event.preventDefault();

        // 		var btn = $(this);
        // 		btn.hasClass('active') ? btn.removeClass('active') : btn.addClass('active');

        // 		return false;
        // 	});
        // }
        // smk_theme_recipe_tools();

        function smk_theme_comment_vote() {
            $('.comment-vote .control').on('click', function() {
                var btn = $(this),
                    container = btn.parent(),
                    counter = container.find('.counter'),
                    total = parseInt(counter.text(), 10);

                if (btn.hasClass('active')) {
                    btn.hasClass('upvote') ? counter.text(total - 1) : counter.text(total + 1);
                    btn.removeClass('active');
                } else {
                    if (container.find('.control').hasClass('active')) {
                        btn.hasClass('upvote') ? counter.text(total + 2) : counter.text(total - 2);
                    } else {
                        btn.hasClass('upvote') ? counter.text(total + 1) : counter.text(total - 1);
                    }
                    container.find('.control').removeClass('active');
                    btn.addClass('active');
                }
            });
        }

        smk_theme_comment_vote();

        /*
	-------------------------------------------------------------------------------
	Uploader
	-------------------------------------------------------------------------------
	*/

        function smk_uploader_auto_remove_file($file, $auto_remove_time) {
            $auto_remove_time = ($auto_remove_time && $auto_remove_time > 1) ? $auto_remove_time : 2000;

            var file = $('#' + $file.id);

            if (file.hasClass('failed')) {
                setTimeout(function() {
                    file.animate({
                        'width': 0,
                        'opacity': 0
                    }, 250, 'swing', function() {
                        file.remove();
                        $('body').trigger('smk_uploader_refresh');
                    });
                }, $auto_remove_time);
            }
        }

        function smk_uploader_error($file, $message, $auto_remove_time) {
            var file = $('#' + $file.id);
            file.addClass('failed');
            if ($message) {
                file.append('<span class="file-error">' + $message + '</span>');
            }

            smk_uploader_auto_remove_file($file, $auto_remove_time);
        }

        function smk_uploader() {
            // Allow uploader only for logged in users.
            if (themeAjax.is_logged_in < 1 || typeof plupload === "undefined")
                return false;

            // All uploaders from current document: jquery object
            var uploaders = $('.smk-uploader');

            // If have uploaders
            if (uploaders.length > 0) {
                // Loop
                $.each(uploaders, function(index, obj) {

                    // Current uploader jquery object
                    var instance = $(uploaders[index]);

                    // Current uploader variables
                    var up_button = instance.find('.upload-button'),
                        up_images_container = instance.find('.the-images'),
                        up_remove_button = '<span class="smk-uploader-remove-file remove" title="' + themeAjax.remove + '"><i class="fa fa-trash"></i></span>',
                        up_progress_bar = '<span class="the-progress-bar"><span></span></span>',
                        up_unique_id = instance.data('u');

                    var up_settings = smkUploadersSettingsJS[up_unique_id],
                        up_multiple = (up_settings.multiple || up_settings.multiple > 1);

                    // Plupload create
                    var uploader = new plupload.Uploader({

                        browse_button: up_button[0],
                        drop_element: up_button[0],
                        container: uploaders[index],
                        url: themeAjax.ajax_url + '?action=smk_uploader&smk_uploader=upload_file&upid=' + up_unique_id,
                        runtimes: 'html5,flash,silverlight,html4',
                        flash_swf_url: themeAjax.includes_url + 'js/plupload/plupload.flash.swf',
                        silverlight_xap_url: themeAjax.includes_url + 'js/plupload/plupload.silverlight.xap',

                        multi_selection: up_multiple,

                        filters: {
                            max_file_size: up_settings.max_file_size,
                            mime_types: [{
                                title: "Files",
                                extensions: up_settings.extensions.join()
                            }, ],
                            prevent_duplicates: true,
                        },

                        init: {
                            PostInit: function() {},

                            FilesAdded: function(up, files) {

                                var total_files = up_images_container.children('.the-image:not(.failed)').length;

                                // console.log(total_files);
                                // console.log(files);
                                // console.log(up_multiple);

                                // If multiple id disabled and no files were added, get first file.
                                if (up_multiple === false && total_files < 1) {
                                    up_button.before('<div class="the-image" id="' + files[0].id + '">' + up_progress_bar + up_remove_button + '</div>');
                                    up_button.hide();
                                } else if (up_multiple) {
                                    plupload.each(files, function(file) {
                                        var the_image = '<div class="the-image" id="' + file.id + '">' + up_progress_bar + up_remove_button + '</div>';
                                        up_button.before(the_image);
                                    });
                                }

                                uploader.start();
                            },

                            UploadProgress: function(up, file) {
                                $('#' + file.id + ' .the-progress-bar > span').css('width', file.percent + '%');
                            },

                            Error: function(up, err) {
                                // console.log(err);

                                if (err.code == -600) {
                                    var message = (err.file.name + ' exceeds the maximum file size allowed.');
                                } else {
                                    var message = ("Error #" + err.code + ": " + err.message);
                                }

                                var the_image = '<div class="the-image failed" id="' + err.file.id + '">' + up_remove_button + '</div>';
                                up_button.before(the_image);

                                smk_uploader_error(err.file, message, 3000);
                            },

                            FileUploaded: function(up, file, result) {
                                if (result.response) {
                                    var rs = JSON.parse(result.response),
                                        _thumb;
                                    if (rs.status && 'success' == rs.status) {

                                        if (rs.data.sizes && rs.data.sizes.thumbnail) {
                                            _thumb = rs.data.sizes.thumbnail.url;
                                        } else {
                                            _thumb = rs.data.icon;
                                        }

                                        var field_id = $('#' + file.id).parents('.smk-image-uploader').data('id'),
                                            hidden_input = '<input type="hidden" value="' + rs.data.id + '" name="' + field_id + '[]" />';

                                        $('#' + file.id).attr('title', file.name).append('<span class="size">' + rs.data.filesizeHumanReadable + '</span><img src="' + _thumb + '" alt="" />' + hidden_input);

                                    } else if (rs.status && 'fail' == rs.status) {
                                        smk_uploader_error(file, rs.data);
                                    } else {
                                        smk_uploader_error(file, 'Bad response');
                                    }

                                } else {
                                    smk_uploader_error(file, 'Upload error');
                                }

                                $('#' + file.id + ' .the-progress-bar').fadeOut(150);
                                uploader.refresh();

                            },

                            UploadComplete: function(up, files) {
                                var completed_files = up_images_container.children('.the-image:not(.failed)');
                                $('.smk-uploader').trigger('smk_uploader_files_uploaded', up, files, completed_files);
                            },
                        },
                    });

                    // Init current uploader
                    uploader.init();

                    $('body').on('smk_uploader_refresh', function() {
                        uploader.refresh();
                        up_button.show();
                    });


                    // Support for focus
                    instance
                        .on('focusin', 'input[type="file"]', function() {
                            up_button.addClass('focused');
                        })
                        .on('focusout', 'input[type="file"]', function() {
                            up_button.removeClass('focused');
                        });
                    // Support for focus end

                }); //$.each();


            }
            /* Remove an image
		-----------------------*/
            $('body').on('click', '.smk-uploader-remove-file', function(event) {
                var file = $(this).parent();

                file.animate({
                    'width': 0,
                    'opacity': 0
                }, 250, 'swing', function() {
                    file.remove();
                    $('body').trigger('smk_uploader_refresh');
                });

            });

        }

        smk_uploader();

        function save_single_recipe_user_images() {
            $('.smk-uploader').on('smk_uploader_files_uploaded', function() {
                if ($('#smk-single-recipe-user-images').length > 0) {

                    var _form = $('#smk-single-recipe-user-images').serialize();

                    $('#smk-single-recipe-user-images').find('img:not(.ignore)').css('opacity', 0.5);

                    jQuery.ajax({
                        type: "POST",
                        url: themeAjax.ajax_url,
                        data: {
                            'action': 'smk_save_single_recipe_user_images',
                            'form': _form,
                        },
                        success: function(response) {
                            console.log(response);
                            $('#smk-single-recipe-user-images').find('img').addClass('ignore').css('opacity', 1);
                            $('body').trigger('smk_uploader_refresh');
                        },
                    });
                }
            });
        }

        save_single_recipe_user_images();

        function delete_single_recipe_user_images() {
            $('.user-image-delete').on('click', function(event) {
                event.preventDefault();

                var _this = $(this),
                    post_id = _this.data('post'),
                    user = _this.data('user'),
                    image = _this.data('image');

                _this.addClass('loading');

                jQuery.ajax({
                    type: "POST",
                    url: themeAjax.ajax_url,
                    data: {
                        'action': 'smk_delete_single_recipe_user_images',
                        'post_id': post_id,
                        'user': user,
                        'image': image,
                    },
                    success: function(response) {
                        console.log(response);
                        _this.parents('.recipe-user-image-in-slider').css('opacity', '0.3');
                        _this.remove();
                    },
                });
            });
        }

        delete_single_recipe_user_images();

        function smk_theme_floating_search() {
            $('.smk-theme-floating-search button').on('click', function() {
                $(this).addClass('loading');
            });
            $('#smk-theme-search-icon, .filter-recipes-pointer').on('click', function(event) {
                if (!$('#the-user-menu').hasClass('breakpoint')) {
                    event.preventDefault();

                    $('body').css({
                        'position': 'fixed',
                        'overflow-y': 'scroll',
                        'left': 0,
                        'right': 0,
                    });

                    $('.smk-theme-floating-search, .smk-recipe-search-overflow').addClass('active');

                    setTimeout(function() {
                        $('.smk-theme-floating-search .smk-field-search_').focus();
                    }, 500);
                }
            });
            $('.smk-recipe-search-overflow, .smk-theme-search-cancel').on('click', function(event) {
                event.preventDefault();
                $('body').css({
                    'position': '',
                    'overflow-y': '',
                    'left': '',
                    'right': '',
                });
                $('.smk-theme-floating-search, .smk-recipe-search-overflow').removeClass('active');
            });

            // Submit floating search form and also clear the empty values before submit
            $('.smk-theme-floating-search form').on('submit', function() {
                $(this).find('button').addClass('loading');

                $(this).find('input[name], select[name]').each(function() {
                    if (!$(this).val()) {
                        $(this).removeAttr('name');
                    }
                });

            });
        }

        smk_theme_floating_search();


        function smk_quick_search_get_results() {
            $('.smk-theme-quick-search form').on('submit', function(event) {
                event.preventDefault();

                var _this = $(this),
                    input = _this.find('input[name="qs"]'),
                    qs = input.val(),
                    number_of_results = _this.parent().data('number_of_results'),
                    results_container = _this.next('.qs-results');

                if (qs.length < 2) {
                    input.css('border-color', '#D33257');
                    if (input.next('.text-red').length < 1) {
                        input.after('<div class="text-red">' + themeAjax.quick_search_more_chars + '</div>');
                    }
                    return;
                } else {
                    input.css('border-color', '');
                    input.next('.text-red').remove();
                }

                results_container.css('opacity', 0.4);

                jQuery.ajax({
                    type: "GET",
                    url: themeAjax.ajax_url,
                    data: {
                        'action': 'smk_quick_search_get_results',
                        'qs': qs,
                        'number_of_results': number_of_results,
                    },
                    success: function(response) {
                        // console.log(response);
                        results_container.html(response).css('opacity', 1);
                    },
                });
            });
        }

        smk_quick_search_get_results();


        /*
	-------------------------------------------------------------------------------
	Post Selector
	-------------------------------------------------------------------------------
	*/
        function smk_post_selector() {
            $('.smk-post-selector-input').on('keyup', function() {
                var _t = $(this);
                var _block = _t.parent();
                var results_block = _block.children('.results');
                var val = _t.val().trim();
                var settings = _t.data('settings');

                if (!val) {
                    results_block.html('');
                    results_block.addClass('disabled');
                    return;
                } else {
                    results_block.removeClass('disabled');
                }


                settings.keyword = val;
                settings.action = 'smk_post_selector';

                _t.addClass('loading-data');


                jQuery.ajax({
                    type: "POST",
                    url: themeAjax.ajax_url,
                    data: settings,
                    success: function(response) {
                        _t.removeClass('loading-data');

                        console.log(response);

                        response = response.trim();

                        if (results_block.hasClass('disabled'))
                            return;

                        if (response) {
                            var results = JSON.parse(response);

                            // Results found
                            if (!results.error) {
                                var html = '';

                                $.each(results, function(id, title) {
                                    html += '<div class="post-item" data-id="' + id + '" data-title="' + title + '"><i class="the-icon fa fa-plus"></i> ' + title + '</div>';
                                });

                                results_block.html(html);
                            } else {
                                results_block.html('');
                            }
                        }
                    },
                });

            });

            $('.smk-post-selector').on('click', '.post-item', function() {
                var _t = $(this);
                var par = _t.parents('.smk-post-selector');
                var id = _t.data('id');
                var title = _t.data('title');
                var name_attr = par.data('name');

                // Already selected?
                if ($(par.children('.selections')).find('[data-id=' + id + ']').length > 0) {
                    _t.slideUp(150);
                    return;
                }

                // Select it!
                var input = '<input type="hidden" value="' + id + '" name="' + name_attr + '[' + id + ']">';
                par.children('.selections').append('<span data-id="' + id + '" class="selection">' + title + input + '</span>');
                _t.slideUp(150);
            });

            $('.smk-post-selector').on('click', '.selection', function() {
                var _t = $(this);
                var par = _t.parents('.smk-post-selector');
                var id = _t.data('id');
                $(this).fadeOut(150, function() {
                    _t.remove();
                    par.find('.post-item[data-id=' + id + ']').slideDown(150);
                });
            });
        }

        smk_post_selector();


        /*
	-------------------------------------------------------------------------------
	WooCommerce
	-------------------------------------------------------------------------------
	*/
        $(document.body).on('added_to_cart', function(event, fragments) {
            if (fragments) {
                var _counter = $('#smk-theme-header-woo-cart-total'),
                    _total = parseInt(_counter.text(), 10),
                    _total = (_total > 0) ? _total : 0;

                _counter.text(_total + 1).show();

                $('#top-wc-header-link').removeAttr('title').removeAttr('data-ztip-title');
                $('#top-wc-header-link').next('.smk-theme-woocommerce-header-widget').css('display', '');
            }
        });

        /*
	-------------------------------------------------------------------------------
	Responsive iframes
	-------------------------------------------------------------------------------
	*/
        $('p iframe').wrap('<div class="iframe-container"></div>');


    });

})(jQuery);