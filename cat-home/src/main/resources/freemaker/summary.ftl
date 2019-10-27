<div id="summaryContent">
    <style>
        th, .alert-content, .metric {
            white-space: nowrap;
        }

        /
        /
        bootstrap css
        html {
            font-family: sans-serif;
            -ms-text-size-adjust: 100%;
            -webkit-text-size-adjust: 100%
        }

        body {
            margin: 0
        }

        article, aside, details, figcaption, figure, footer, header, hgroup, main, nav, section, summary {
            display: block
        }

        audio, canvas, progress, video {
            display: inline-block;
            vertical-align: baseline
        }

        audio:not([controls]) {
            display: none;
            height: 0
        }

        [hidden], template {
            display: none
        }

        a {
            background: transparent
        }

        a:active, a:hover {
            outline: 0
        }

        abbr[title] {
            border-bottom: 1px dotted
        }

        b, strong {
            font-weight: bold
        }

        dfn {
            font-style: italic
        }

        h1 {
            font-size: 2em;
            margin: 0.67em 0
        }

        mark {
            background: #ff0;
            color: #000
        }

        small {
            font-size: 80%
        }

        sub, sup {
            font-size: 75%;
            line-height: 0;
            position: relative;
            vertical-align: baseline
        }

        sup {
            top: -0.5em
        }

        sub {
            bottom: -0.25em
        }

        img {
            border: 0
        }

        svg:not(:root) {
            overflow: hidden
        }

        figure {
            margin: 1em 40px
        }

        hr {
            -moz-box-sizing: content-box;
            box-sizing: content-box;
            height: 0
        }

        pre {
            overflow: auto
        }

        code, kbd, pre, samp {
            font-family: monospace, monospace;
            font-size: 1em
        }

        button, input, optgroup, select, textarea {
            color: inherit;
            font: inherit;
            margin: 0
        }

        button {
            overflow: visible
        }

        button, select {
            text-transform: none
        }

        button, html input[type="button"], input[type="reset"], input[type="submit"] {
            -webkit-appearance: button;
            cursor: pointer
        }

        button[disabled], html input[disabled] {
            cursor: default
        }

        button::-moz-focus-inner, input::-moz-focus-inner {
            border: 0;
            padding: 0
        }

        input {
            line-height: normal
        }

        input[type="checkbox"], input[type="radio"] {
            box-sizing: border-box;
            padding: 0
        }

        input[type="number"]::-webkit-inner-spin-button, input[type="number"]::-webkit-outer-spin-button {
            height: auto
        }

        input[type="search"] {
            -webkit-appearance: textfield;
            -moz-box-sizing: content-box;
            -webkit-box-sizing: content-box;
            box-sizing: content-box
        }

        input[type="search"]::-webkit-search-cancel-button, input[type="search"]::-webkit-search-decoration {
            -webkit-appearance: none
        }

        fieldset {
            border: 1px solid #c0c0c0;
            margin: 0 2px;
            padding: 0.35em 0.625em 0.75em
        }

        legend {
            border: 0;
            padding: 0
        }

        textarea {
            overflow: auto
        }

        optgroup {
            font-weight: bold
        }

        table {
            border-collapse: collapse;
            border-spacing: 0
        }

        td, th {
            padding: 0
        }

        @media print {
            * {
                text-shadow: none !important;
                color: #000 !important;
                background: transparent !important;
                box-shadow: none !important
            }

            a, a:visited {
                text-decoration: underline
            }

            a[href]:after {
                content: " (" attr(href) ")"
            }

            abbr[title]:after {
                content: " (" attr(title) ")"
            }

            a[href^="javascript:"]:after, a[href^="#"]:after {
                content: ""
            }

            pre, blockquote {
                border: 1px solid #999;
                page-break-inside: avoid
            }

            thead {
                display: table-header-group
            }

            tr, img {
                page-break-inside: avoid
            }

            img {
                max-width: 100% !important
            }

            p, h2, h3 {
                orphans: 3;
                widows: 3
            }

            h2, h3 {
                page-break-after: avoid
            }

            select {
                background: #fff !important
            }

            .navbar {
                display: none
            }

            .table td, .table th {
                background-color: #fff !important
            }

            .btn > .caret, .dropup > .btn > .caret {
                border-top-color: #000 !important
            }

            .label {
                border: 1px solid #000
            }

            .table {
                border-collapse: collapse !important
            }

            .table-bordered th, .table-bordered td {
                border: 1px solid #ddd !important
            }
        }

        * {
            -webkit-box-sizing: border-box;
            -moz-box-sizing: border-box;
            box-sizing: border-box
        }

        *:before, *:after {
            -webkit-box-sizing: border-box;
            -moz-box-sizing: border-box;
            box-sizing: border-box
        }

        html {
            font-size: 10px;
            -webkit-tap-highlight-color: rgba(0, 0, 0, 0)
        }

        body {
            font-family: "Helvetica Neue", Helvetica, Arial, sans-serif;
            font-size: 14px;
            line-height: 1.42857143;
            color: #333;
            background-color: #fff
        }

        input, button, select, textarea {
            font-family: inherit;
            font-size: inherit;
            line-height: inherit
        }

        a {
            color: #428bca;
            text-decoration: none
        }

        a:hover, a:focus {
            color: #2a6496;
            text-decoration: underline
        }

        a:focus {
            outline: thin dotted;
            outline: 5px auto -webkit-focus-ring-color;
            outline-offset: -2px
        }

        figure {
            margin: 0
        }

        img {
            vertical-align: middle
        }

        .img-responsive {
            display: block;
            width: 100% \9;
            max-width: 100%;
            height: auto
        }

        .img-rounded {
            border-radius: 6px
        }

        .img-thumbnail {
            padding: 4px;
            line-height: 1.42857143;
            background-color: #fff;
            border: 1px solid #ddd;
            border-radius: 4px;
            -webkit-transition: all .2s ease-in-out;
            -o-transition: all .2s ease-in-out;
            transition: all .2s ease-in-out;
            display: inline-block;
            width: 100% \9;
            max-width: 100%;
            height: auto
        }

        .img-circle {
            border-radius: 50%
        }

        hr {
            margin-top: 20px;
            margin-bottom: 20px;
            border: 0;
            border-top: 1px solid #eee
        }

        .sr-only {
            position: absolute;
            width: 1px;
            height: 1px;
            margin: -1px;
            padding: 0;
            overflow: hidden;
            clip: rect(0, 0, 0, 0);
            border: 0
        }

        .sr-only-focusable:active, .sr-only-focusable:focus {
            position: static;
            width: auto;
            height: auto;
            margin: 0;
            overflow: visible;
            clip: auto
        }

        h1, h2, h3, h4, h5, h6, .h1, .h2, .h3, .h4, .h5, .h6 {
            font-family: inherit;
            font-weight: 500;
            line-height: 1.1;
            color: inherit
        }

        h1 small, h2 small, h3 small, h4 small, h5 small, h6 small, .h1 small, .h2 small, .h3 small, .h4 small, .h5 small, .h6 small, h1 .small, h2 .small, h3 .small, h4 .small, h5 .small, h6 .small, .h1 .small, .h2 .small, .h3 .small, .h4 .small, .h5 .small, .h6 .small {
            font-weight: normal;
            line-height: 1;
            color: #777
        }

        h1, .h1, h2, .h2, h3, .h3 {
            margin-top: 20px;
            margin-bottom: 10px
        }

        h1 small, .h1 small, h2 small, .h2 small, h3 small, .h3 small, h1 .small, .h1 .small, h2 .small, .h2 .small, h3 .small, .h3 .small {
            font-size: 65%
        }

        h4, .h4, h5, .h5, h6, .h6 {
            margin-top: 10px;
            margin-bottom: 10px
        }

        h4 small, .h4 small, h5 small, .h5 small, h6 small, .h6 small, h4 .small, .h4 .small, h5 .small, .h5 .small, h6 .small, .h6 .small {
            font-size: 75%
        }

        h1, .h1 {
            font-size: 36px
        }

        h2, .h2 {
            font-size: 30px
        }

        h3, .h3 {
            font-size: 24px
        }

        h4, .h4 {
            font-size: 18px
        }

        h5, .h5 {
            font-size: 14px
        }

        h6, .h6 {
            font-size: 12px
        }

        p {
            margin: 0 0 10px
        }

        .lead {
            margin-bottom: 20px;
            font-size: 16px;
            font-weight: 300;
            line-height: 1.4
        }

        @media (min-width: 768px) {
            .lead {
                font-size: 21px
            }
        }

        small, .small {
            font-size: 85%
        }

        cite {
            font-style: normal
        }

        mark, .mark {
            background-color: #fcf8e3;
            padding: .2em
        }

        .text-left {
            text-align: left
        }

        .text-right {
            text-align: right
        }

        .text-center {
            text-align: center
        }

        .text-justify {
            text-align: justify
        }

        .text-nowrap {
            white-space: nowrap
        }

        .text-lowercase {
            text-transform: lowercase
        }

        .text-uppercase {
            text-transform: uppercase
        }

        .text-capitalize {
            text-transform: capitalize
        }

        .text-muted {
            color: #777
        }

        .text-primary {
            color: #428bca
        }

        a.text-primary:hover {
            color: #3071a9
        }

        .text-success {
            color: #3c763d
        }

        a.text-success:hover {
            color: #2b542c
        }

        .text-info {
            color: #31708f
        }

        a.text-info:hover {
            color: #245269
        }

        .text-warning {
            color: #8a6d3b
        }

        a.text-warning:hover {
            color: #66512c
        }

        .text-danger {
            color: #a94442
        }

        a.text-danger:hover {
            color: #843534
        }

        .bg-primary {
            color: #fff;
            background-color: #428bca
        }

        a.bg-primary:hover {
            background-color: #3071a9
        }

        .bg-success {
            background-color: #dff0d8
        }

        a.bg-success:hover {
            background-color: #c1e2b3
        }

        .bg-info {
            background-color: #d9edf7
        }

        a.bg-info:hover {
            background-color: #afd9ee
        }

        .bg-warning {
            background-color: #fcf8e3
        }

        a.bg-warning:hover {
            background-color: #f7ecb5
        }

        .bg-danger {
            background-color: #f2dede
        }

        a.bg-danger:hover {
            background-color: #e4b9b9
        }

        .page-header {
            padding-bottom: 9px;
            margin: 40px 0 20px;
            border-bottom: 1px solid #eee
        }

        ul, ol {
            margin-top: 0;
            margin-bottom: 10px
        }

        ul ul, ol ul, ul ol, ol ol {
            margin-bottom: 0
        }

        .list-unstyled {
            padding-left: 0;
            list-style: none
        }

        .list-inline {
            padding-left: 0;
            list-style: none;
            margin-left: -5px
        }

        .list-inline > li {
            display: inline-block;
            padding-left: 5px;
            padding-right: 5px
        }

        dl {
            margin-top: 0;
            margin-bottom: 20px
        }

        dt, dd {
            line-height: 1.42857143
        }

        dt {
            font-weight: bold
        }

        dd {
            margin-left: 0
        }

        @media (min-width: 768px) {
            .dl-horizontal dt {
                float: left;
                width: 160px;
                clear: left;
                text-align: right;
                overflow: hidden;
                text-overflow: ellipsis;
                white-space: nowrap
            }

            .dl-horizontal dd {
                margin-left: 180px
            }
        }

        abbr[title], abbr[data-original-title] {
            cursor: help;
            border-bottom: 1px dotted #777
        }

        .initialism {
            font-size: 90%;
            text-transform: uppercase
        }

        blockquote {
            padding: 10px 20px;
            margin: 0 0 20px;
            font-size: 17.5px;
            border-left: 5px solid #eee
        }

        blockquote p:last-child, blockquote ul:last-child, blockquote ol:last-child {
            margin-bottom: 0
        }

        blockquote footer, blockquote small, blockquote .small {
            display: block;
            font-size: 80%;
            line-height: 1.42857143;
            color: #777
        }

        blockquote footer:before, blockquote small:before, blockquote .small:before {
            content: '\2014 \00A0'
        }

        .blockquote-reverse, blockquote.pull-right {
            padding-right: 15px;
            padding-left: 0;
            border-right: 5px solid #eee;
            border-left: 0;
            text-align: right
        }

        .blockquote-reverse footer:before, blockquote.pull-right footer:before, .blockquote-reverse small:before, blockquote.pull-right small:before, .blockquote-reverse .small:before, blockquote.pull-right .small:before {
            content: ''
        }

        .blockquote-reverse footer:after, blockquote.pull-right footer:after, .blockquote-reverse small:after, blockquote.pull-right small:after, .blockquote-reverse .small:after, blockquote.pull-right .small:after {
            content: '\00A0 \2014'
        }

        blockquote:before, blockquote:after {
            content: ""
        }

        address {
            margin-bottom: 20px;
            font-style: normal;
            line-height: 1.42857143
        }

        code, kbd, pre, samp {
            font-family: Menlo, Monaco, Consolas, "Courier New", monospace
        }

        code {
            padding: 2px 4px;
            font-size: 90%;
            color: #c7254e;
            background-color: #f9f2f4;
            border-radius: 4px
        }

        kbd {
            padding: 2px 4px;
            font-size: 90%;
            color: #fff;
            background-color: #333;
            border-radius: 3px;
            box-shadow: inset 0 -1px 0 rgba(0, 0, 0, 0.25)
        }

        kbd kbd {
            padding: 0;
            font-size: 100%;
            box-shadow: none
        }

        pre {
            display: block;
            padding: 9.5px;
            margin: 0 0 10px;
            font-size: 13px;
            line-height: 1.42857143;
            word-break: break-all;
            word-wrap: break-word;
            color: #333;
            background-color: #f5f5f5;
            border: 1px solid #ccc;
            border-radius: 4px
        }

        pre code {
            padding: 0;
            font-size: inherit;
            color: inherit;
            white-space: pre-wrap;
            background-color: transparent;
            border-radius: 0
        }

        .pre-scrollable {
            max-height: 340px;
            overflow-y: scroll
        }

        .container {
            margin-right: auto;
            margin-left: auto;
            padding-left: 15px;
            padding-right: 15px
        }

        @media (min-width: 768px) {
            .container {
                width: 750px
            }
        }

        @media (min-width: 992px) {
            .container {
                width: 970px
            }
        }

        @media (min-width: 1200px) {
            .container {
                width: 1170px
            }
        }

        .container-fluid {
            margin-right: auto;
            margin-left: auto;
            padding-left: 15px;
            padding-right: 15px
        }

        .row {
            margin-left: -15px;
            margin-right: -15px
        }

        .col-xs-1, .col-sm-1, .col-md-1, .col-lg-1, .col-xs-2, .col-sm-2, .col-md-2, .col-lg-2, .col-xs-3, .col-sm-3, .col-md-3, .col-lg-3, .col-xs-4, .col-sm-4, .col-md-4, .col-lg-4, .col-xs-5, .col-sm-5, .col-md-5, .col-lg-5, .col-xs-6, .col-sm-6, .col-md-6, .col-lg-6, .col-xs-7, .col-sm-7, .col-md-7, .col-lg-7, .col-xs-8, .col-sm-8, .col-md-8, .col-lg-8, .col-xs-9, .col-sm-9, .col-md-9, .col-lg-9, .col-xs-10, .col-sm-10, .col-md-10, .col-lg-10, .col-xs-11, .col-sm-11, .col-md-11, .col-lg-11, .col-xs-12, .col-sm-12, .col-md-12, .col-lg-12 {
            position: relative;
            min-height: 1px;
            padding-left: 15px;
            padding-right: 15px
        }

        .col-xs-1, .col-xs-2, .col-xs-3, .col-xs-4, .col-xs-5, .col-xs-6, .col-xs-7, .col-xs-8, .col-xs-9, .col-xs-10, .col-xs-11, .col-xs-12 {
            float: left
        }

        .col-xs-12 {
            width: 100%
        }

        .col-xs-11 {
            width: 91.66666667%
        }

        .col-xs-10 {
            width: 83.33333333%
        }

        .col-xs-9 {
            width: 75%
        }

        .col-xs-8 {
            width: 66.66666667%
        }

        .col-xs-7 {
            width: 58.33333333%
        }

        .col-xs-6 {
            width: 50%
        }

        .col-xs-5 {
            width: 41.66666667%
        }

        .col-xs-4 {
            width: 33.33333333%
        }

        .col-xs-3 {
            width: 25%
        }

        .col-xs-2 {
            width: 16.66666667%
        }

        .col-xs-1 {
            width: 8.33333333%
        }

        .col-xs-pull-12 {
            right: 100%
        }

        .col-xs-pull-11 {
            right: 91.66666667%
        }

        .col-xs-pull-10 {
            right: 83.33333333%
        }

        .col-xs-pull-9 {
            right: 75%
        }

        .col-xs-pull-8 {
            right: 66.66666667%
        }

        .col-xs-pull-7 {
            right: 58.33333333%
        }

        .col-xs-pull-6 {
            right: 50%
        }

        .col-xs-pull-5 {
            right: 41.66666667%
        }

        .col-xs-pull-4 {
            right: 33.33333333%
        }

        .col-xs-pull-3 {
            right: 25%
        }

        .col-xs-pull-2 {
            right: 16.66666667%
        }

        .col-xs-pull-1 {
            right: 8.33333333%
        }

        .col-xs-pull-0 {
            right: auto
        }

        .col-xs-push-12 {
            left: 100%
        }

        .col-xs-push-11 {
            left: 91.66666667%
        }

        .col-xs-push-10 {
            left: 83.33333333%
        }

        .col-xs-push-9 {
            left: 75%
        }

        .col-xs-push-8 {
            left: 66.66666667%
        }

        .col-xs-push-7 {
            left: 58.33333333%
        }

        .col-xs-push-6 {
            left: 50%
        }

        .col-xs-push-5 {
            left: 41.66666667%
        }

        .col-xs-push-4 {
            left: 33.33333333%
        }

        .col-xs-push-3 {
            left: 25%
        }

        .col-xs-push-2 {
            left: 16.66666667%
        }

        .col-xs-push-1 {
            left: 8.33333333%
        }

        .col-xs-push-0 {
            left: auto
        }

        .col-xs-offset-12 {
            margin-left: 100%
        }

        .col-xs-offset-11 {
            margin-left: 91.66666667%
        }

        .col-xs-offset-10 {
            margin-left: 83.33333333%
        }

        .col-xs-offset-9 {
            margin-left: 75%
        }

        .col-xs-offset-8 {
            margin-left: 66.66666667%
        }

        .col-xs-offset-7 {
            margin-left: 58.33333333%
        }

        .col-xs-offset-6 {
            margin-left: 50%
        }

        .col-xs-offset-5 {
            margin-left: 41.66666667%
        }

        .col-xs-offset-4 {
            margin-left: 33.33333333%
        }

        .col-xs-offset-3 {
            margin-left: 25%
        }

        .col-xs-offset-2 {
            margin-left: 16.66666667%
        }

        .col-xs-offset-1 {
            margin-left: 8.33333333%
        }

        .col-xs-offset-0 {
            margin-left: 0
        }

        @media (min-width: 768px) {
            .col-sm-1, .col-sm-2, .col-sm-3, .col-sm-4, .col-sm-5, .col-sm-6, .col-sm-7, .col-sm-8, .col-sm-9, .col-sm-10, .col-sm-11, .col-sm-12 {
                float: left
            }

            .col-sm-12 {
                width: 100%
            }

            .col-sm-11 {
                width: 91.66666667%
            }

            .col-sm-10 {
                width: 83.33333333%
            }

            .col-sm-9 {
                width: 75%
            }

            .col-sm-8 {
                width: 66.66666667%
            }

            .col-sm-7 {
                width: 58.33333333%
            }

            .col-sm-6 {
                width: 50%
            }

            .col-sm-5 {
                width: 41.66666667%
            }

            .col-sm-4 {
                width: 33.33333333%
            }

            .col-sm-3 {
                width: 25%
            }

            .col-sm-2 {
                width: 16.66666667%
            }

            .col-sm-1 {
                width: 8.33333333%
            }

            .col-sm-pull-12 {
                right: 100%
            }

            .col-sm-pull-11 {
                right: 91.66666667%
            }

            .col-sm-pull-10 {
                right: 83.33333333%
            }

            .col-sm-pull-9 {
                right: 75%
            }

            .col-sm-pull-8 {
                right: 66.66666667%
            }

            .col-sm-pull-7 {
                right: 58.33333333%
            }

            .col-sm-pull-6 {
                right: 50%
            }

            .col-sm-pull-5 {
                right: 41.66666667%
            }

            .col-sm-pull-4 {
                right: 33.33333333%
            }

            .col-sm-pull-3 {
                right: 25%
            }

            .col-sm-pull-2 {
                right: 16.66666667%
            }

            .col-sm-pull-1 {
                right: 8.33333333%
            }

            .col-sm-pull-0 {
                right: auto
            }

            .col-sm-push-12 {
                left: 100%
            }

            .col-sm-push-11 {
                left: 91.66666667%
            }

            .col-sm-push-10 {
                left: 83.33333333%
            }

            .col-sm-push-9 {
                left: 75%
            }

            .col-sm-push-8 {
                left: 66.66666667%
            }

            .col-sm-push-7 {
                left: 58.33333333%
            }

            .col-sm-push-6 {
                left: 50%
            }

            .col-sm-push-5 {
                left: 41.66666667%
            }

            .col-sm-push-4 {
                left: 33.33333333%
            }

            .col-sm-push-3 {
                left: 25%
            }

            .col-sm-push-2 {
                left: 16.66666667%
            }

            .col-sm-push-1 {
                left: 8.33333333%
            }

            .col-sm-push-0 {
                left: auto
            }

            .col-sm-offset-12 {
                margin-left: 100%
            }

            .col-sm-offset-11 {
                margin-left: 91.66666667%
            }

            .col-sm-offset-10 {
                margin-left: 83.33333333%
            }

            .col-sm-offset-9 {
                margin-left: 75%
            }

            .col-sm-offset-8 {
                margin-left: 66.66666667%
            }

            .col-sm-offset-7 {
                margin-left: 58.33333333%
            }

            .col-sm-offset-6 {
                margin-left: 50%
            }

            .col-sm-offset-5 {
                margin-left: 41.66666667%
            }

            .col-sm-offset-4 {
                margin-left: 33.33333333%
            }

            .col-sm-offset-3 {
                margin-left: 25%
            }

            .col-sm-offset-2 {
                margin-left: 16.66666667%
            }

            .col-sm-offset-1 {
                margin-left: 8.33333333%
            }

            .col-sm-offset-0 {
                margin-left: 0
            }
        }

        @media (min-width: 992px) {
            .col-md-1, .col-md-2, .col-md-3, .col-md-4, .col-md-5, .col-md-6, .col-md-7, .col-md-8, .col-md-9, .col-md-10, .col-md-11, .col-md-12 {
                float: left
            }

            .col-md-12 {
                width: 100%
            }

            .col-md-11 {
                width: 91.66666667%
            }

            .col-md-10 {
                width: 83.33333333%
            }

            .col-md-9 {
                width: 75%
            }

            .col-md-8 {
                width: 66.66666667%
            }

            .col-md-7 {
                width: 58.33333333%
            }

            .col-md-6 {
                width: 50%
            }

            .col-md-5 {
                width: 41.66666667%
            }

            .col-md-4 {
                width: 33.33333333%
            }

            .col-md-3 {
                width: 25%
            }

            .col-md-2 {
                width: 16.66666667%
            }

            .col-md-1 {
                width: 8.33333333%
            }

            .col-md-pull-12 {
                right: 100%
            }

            .col-md-pull-11 {
                right: 91.66666667%
            }

            .col-md-pull-10 {
                right: 83.33333333%
            }

            .col-md-pull-9 {
                right: 75%
            }

            .col-md-pull-8 {
                right: 66.66666667%
            }

            .col-md-pull-7 {
                right: 58.33333333%
            }

            .col-md-pull-6 {
                right: 50%
            }

            .col-md-pull-5 {
                right: 41.66666667%
            }

            .col-md-pull-4 {
                right: 33.33333333%
            }

            .col-md-pull-3 {
                right: 25%
            }

            .col-md-pull-2 {
                right: 16.66666667%
            }

            .col-md-pull-1 {
                right: 8.33333333%
            }

            .col-md-pull-0 {
                right: auto
            }

            .col-md-push-12 {
                left: 100%
            }

            .col-md-push-11 {
                left: 91.66666667%
            }

            .col-md-push-10 {
                left: 83.33333333%
            }

            .col-md-push-9 {
                left: 75%
            }

            .col-md-push-8 {
                left: 66.66666667%
            }

            .col-md-push-7 {
                left: 58.33333333%
            }

            .col-md-push-6 {
                left: 50%
            }

            .col-md-push-5 {
                left: 41.66666667%
            }

            .col-md-push-4 {
                left: 33.33333333%
            }

            .col-md-push-3 {
                left: 25%
            }

            .col-md-push-2 {
                left: 16.66666667%
            }

            .col-md-push-1 {
                left: 8.33333333%
            }

            .col-md-push-0 {
                left: auto
            }

            .col-md-offset-12 {
                margin-left: 100%
            }

            .col-md-offset-11 {
                margin-left: 91.66666667%
            }

            .col-md-offset-10 {
                margin-left: 83.33333333%
            }

            .col-md-offset-9 {
                margin-left: 75%
            }

            .col-md-offset-8 {
                margin-left: 66.66666667%
            }

            .col-md-offset-7 {
                margin-left: 58.33333333%
            }

            .col-md-offset-6 {
                margin-left: 50%
            }

            .col-md-offset-5 {
                margin-left: 41.66666667%
            }

            .col-md-offset-4 {
                margin-left: 33.33333333%
            }

            .col-md-offset-3 {
                margin-left: 25%
            }

            .col-md-offset-2 {
                margin-left: 16.66666667%
            }

            .col-md-offset-1 {
                margin-left: 8.33333333%
            }

            .col-md-offset-0 {
                margin-left: 0
            }
        }

        @media (min-width: 1200px) {
            .col-lg-1, .col-lg-2, .col-lg-3, .col-lg-4, .col-lg-5, .col-lg-6, .col-lg-7, .col-lg-8, .col-lg-9, .col-lg-10, .col-lg-11, .col-lg-12 {
                float: left
            }

            .col-lg-12 {
                width: 100%
            }

            .col-lg-11 {
                width: 91.66666667%
            }

            .col-lg-10 {
                width: 83.33333333%
            }

            .col-lg-9 {
                width: 75%
            }

            .col-lg-8 {
                width: 66.66666667%
            }

            .col-lg-7 {
                width: 58.33333333%
            }

            .col-lg-6 {
                width: 50%
            }

            .col-lg-5 {
                width: 41.66666667%
            }

            .col-lg-4 {
                width: 33.33333333%
            }

            .col-lg-3 {
                width: 25%
            }

            .col-lg-2 {
                width: 16.66666667%
            }

            .col-lg-1 {
                width: 8.33333333%
            }

            .col-lg-pull-12 {
                right: 100%
            }

            .col-lg-pull-11 {
                right: 91.66666667%
            }

            .col-lg-pull-10 {
                right: 83.33333333%
            }

            .col-lg-pull-9 {
                right: 75%
            }

            .col-lg-pull-8 {
                right: 66.66666667%
            }

            .col-lg-pull-7 {
                right: 58.33333333%
            }

            .col-lg-pull-6 {
                right: 50%
            }

            .col-lg-pull-5 {
                right: 41.66666667%
            }

            .col-lg-pull-4 {
                right: 33.33333333%
            }

            .col-lg-pull-3 {
                right: 25%
            }

            .col-lg-pull-2 {
                right: 16.66666667%
            }

            .col-lg-pull-1 {
                right: 8.33333333%
            }

            .col-lg-pull-0 {
                right: auto
            }

            .col-lg-push-12 {
                left: 100%
            }

            .col-lg-push-11 {
                left: 91.66666667%
            }

            .col-lg-push-10 {
                left: 83.33333333%
            }

            .col-lg-push-9 {
                left: 75%
            }

            .col-lg-push-8 {
                left: 66.66666667%
            }

            .col-lg-push-7 {
                left: 58.33333333%
            }

            .col-lg-push-6 {
                left: 50%
            }

            .col-lg-push-5 {
                left: 41.66666667%
            }

            .col-lg-push-4 {
                left: 33.33333333%
            }

            .col-lg-push-3 {
                left: 25%
            }

            .col-lg-push-2 {
                left: 16.66666667%
            }

            .col-lg-push-1 {
                left: 8.33333333%
            }

            .col-lg-push-0 {
                left: auto
            }

            .col-lg-offset-12 {
                margin-left: 100%
            }

            .col-lg-offset-11 {
                margin-left: 91.66666667%
            }

            .col-lg-offset-10 {
                margin-left: 83.33333333%
            }

            .col-lg-offset-9 {
                margin-left: 75%
            }

            .col-lg-offset-8 {
                margin-left: 66.66666667%
            }

            .col-lg-offset-7 {
                margin-left: 58.33333333%
            }

            .col-lg-offset-6 {
                margin-left: 50%
            }

            .col-lg-offset-5 {
                margin-left: 41.66666667%
            }

            .col-lg-offset-4 {
                margin-left: 33.33333333%
            }

            .col-lg-offset-3 {
                margin-left: 25%
            }

            .col-lg-offset-2 {
                margin-left: 16.66666667%
            }

            .col-lg-offset-1 {
                margin-left: 8.33333333%
            }

            .col-lg-offset-0 {
                margin-left: 0
            }
        }

        table {
            background-color: transparent
        }

        th {
            text-align: left
        }

        .table {
            width: 100%;
            max-width: 100%;
            margin-bottom: 20px
        }

        .table > thead > tr > th, .table > tbody > tr > th, .table > tfoot > tr > th, .table > thead > tr > td, .table > tbody > tr > td, .table > tfoot > tr > td {
            padding: 8px;
            line-height: 1.42857143;
            vertical-align: top;
            border-top: 1px solid #ddd
        }

        .table > thead > tr > th {
            vertical-align: bottom;
            border-bottom: 2px solid #ddd
        }

        .table > caption + thead > tr:first-child > th, .table > colgroup + thead > tr:first-child > th, .table > thead:first-child > tr:first-child > th, .table > caption + thead > tr:first-child > td, .table > colgroup + thead > tr:first-child > td, .table > thead:first-child > tr:first-child > td {
            border-top: 0
        }

        .table > tbody + tbody {
            border-top: 2px solid #ddd
        }

        .table .table {
            background-color: #fff
        }

        .table-condensed > thead > tr > th, .table-condensed > tbody > tr > th, .table-condensed > tfoot > tr > th, .table-condensed > thead > tr > td, .table-condensed > tbody > tr > td, .table-condensed > tfoot > tr > td {
            padding: 5px
        }

        .table-bordered {
            border: 1px solid #ddd
        }

        .table-bordered > thead > tr > th, .table-bordered > tbody > tr > th, .table-bordered > tfoot > tr > th, .table-bordered > thead > tr > td, .table-bordered > tbody > tr > td, .table-bordered > tfoot > tr > td {
            border: 1px solid #ddd
        }

        .table-bordered > thead > tr > th, .table-bordered > thead > tr > td {
            border-bottom-width: 2px
        }

        .table-striped > tbody > tr:nth-child(odd) > td, .table-striped > tbody > tr:nth-child(odd) > th {
            background-color: #f9f9f9
        }

        .table-hover > tbody > tr:hover > td, .table-hover > tbody > tr:hover > th {
            background-color: #f5f5f5
        }

        table col[class*="col-"] {
            position: static;
            float: none;
            display: table-column
        }

        table td[class*="col-"], table th[class*="col-"] {
            position: static;
            float: none;
            display: table-cell
        }

        .table > thead > tr > td.active, .table > tbody > tr > td.active, .table > tfoot > tr > td.active, .table > thead > tr > th.active, .table > tbody > tr > th.active, .table > tfoot > tr > th.active, .table > thead > tr.active > td, .table > tbody > tr.active > td, .table > tfoot > tr.active > td, .table > thead > tr.active > th, .table > tbody > tr.active > th, .table > tfoot > tr.active > th {
            background-color: #f5f5f5
        }

        .table-hover > tbody > tr > td.active:hover, .table-hover > tbody > tr > th.active:hover, .table-hover > tbody > tr.active:hover > td, .table-hover > tbody > tr:hover > .active, .table-hover > tbody > tr.active:hover > th {
            background-color: #e8e8e8
        }

        .table > thead > tr > td.success, .table > tbody > tr > td.success, .table > tfoot > tr > td.success, .table > thead > tr > th.success, .table > tbody > tr > th.success, .table > tfoot > tr > th.success, .table > thead > tr.success > td, .table > tbody > tr.success > td, .table > tfoot > tr.success > td, .table > thead > tr.success > th, .table > tbody > tr.success > th, .table > tfoot > tr.success > th {
            background-color: #dff0d8
        }

        .table-hover > tbody > tr > td.success:hover, .table-hover > tbody > tr > th.success:hover, .table-hover > tbody > tr.success:hover > td, .table-hover > tbody > tr:hover > .success, .table-hover > tbody > tr.success:hover > th {
            background-color: #d0e9c6
        }

        .table > thead > tr > td.info, .table > tbody > tr > td.info, .table > tfoot > tr > td.info, .table > thead > tr > th.info, .table > tbody > tr > th.info, .table > tfoot > tr > th.info, .table > thead > tr.info > td, .table > tbody > tr.info > td, .table > tfoot > tr.info > td, .table > thead > tr.info > th, .table > tbody > tr.info > th, .table > tfoot > tr.info > th {
            background-color: #d9edf7
        }

        .table-hover > tbody > tr > td.info:hover, .table-hover > tbody > tr > th.info:hover, .table-hover > tbody > tr.info:hover > td, .table-hover > tbody > tr:hover > .info, .table-hover > tbody > tr.info:hover > th {
            background-color: #c4e3f3
        }

        .table > thead > tr > td.warning, .table > tbody > tr > td.warning, .table > tfoot > tr > td.warning, .table > thead > tr > th.warning, .table > tbody > tr > th.warning, .table > tfoot > tr > th.warning, .table > thead > tr.warning > td, .table > tbody > tr.warning > td, .table > tfoot > tr.warning > td, .table > thead > tr.warning > th, .table > tbody > tr.warning > th, .table > tfoot > tr.warning > th {
            background-color: #fcf8e3
        }

        .table-hover > tbody > tr > td.warning:hover, .table-hover > tbody > tr > th.warning:hover, .table-hover > tbody > tr.warning:hover > td, .table-hover > tbody > tr:hover > .warning, .table-hover > tbody > tr.warning:hover > th {
            background-color: #faf2cc
        }

        .table > thead > tr > td.danger, .table > tbody > tr > td.danger, .table > tfoot > tr > td.danger, .table > thead > tr > th.danger, .table > tbody > tr > th.danger, .table > tfoot > tr > th.danger, .table > thead > tr.danger > td, .table > tbody > tr.danger > td, .table > tfoot > tr.danger > td, .table > thead > tr.danger > th, .table > tbody > tr.danger > th, .table > tfoot > tr.danger > th {
            background-color: #f2dede
        }

        .table-hover > tbody > tr > td.danger:hover, .table-hover > tbody > tr > th.danger:hover, .table-hover > tbody > tr.danger:hover > td, .table-hover > tbody > tr:hover > .danger, .table-hover > tbody > tr.danger:hover > th {
            background-color: #ebcccc
        }

        @media screen and (max-width: 767px) {
            .table-responsive {
                width: 100%;
                margin-bottom: 15px;
                overflow-y: hidden;
                overflow-x: auto;
                -ms-overflow-style: -ms-autohiding-scrollbar;
                border: 1px solid #ddd;
                -webkit-overflow-scrolling: touch
            }

            .table-responsive > .table {
                margin-bottom: 0
            }

            .table-responsive > .table > thead > tr > th, .table-responsive > .table > tbody > tr > th, .table-responsive > .table > tfoot > tr > th, .table-responsive > .table > thead > tr > td, .table-responsive > .table > tbody > tr > td, .table-responsive > .table > tfoot > tr > td {
                white-space: nowrap
            }

            .table-responsive > .table-bordered {
                border: 0
            }

            .table-responsive > .table-bordered > thead > tr > th:first-child, .table-responsive > .table-bordered > tbody > tr > th:first-child, .table-responsive > .table-bordered > tfoot > tr > th:first-child, .table-responsive > .table-bordered > thead > tr > td:first-child, .table-responsive > .table-bordered > tbody > tr > td:first-child, .table-responsive > .table-bordered > tfoot > tr > td:first-child {
                border-left: 0
            }

            .table-responsive > .table-bordered > thead > tr > th:last-child, .table-responsive > .table-bordered > tbody > tr > th:last-child, .table-responsive > .table-bordered > tfoot > tr > th:last-child, .table-responsive > .table-bordered > thead > tr > td:last-child, .table-responsive > .table-bordered > tbody > tr > td:last-child, .table-responsive > .table-bordered > tfoot > tr > td:last-child {
                border-right: 0
            }

            .table-responsive > .table-bordered > tbody > tr:last-child > th, .table-responsive > .table-bordered > tfoot > tr:last-child > th, .table-responsive > .table-bordered > tbody > tr:last-child > td, .table-responsive > .table-bordered > tfoot > tr:last-child > td {
                border-bottom: 0
            }
        }

        fieldset {
            padding: 0;
            margin: 0;
            border: 0;
            min-width: 0
        }

        legend {
            display: block;
            width: 100%;
            padding: 0;
            margin-bottom: 20px;
            font-size: 21px;
            line-height: inherit;
            color: #333;
            border: 0;
            border-bottom: 1px solid #e5e5e5
        }

        label {
            display: inline-block;
            max-width: 100%;
            margin-bottom: 5px;
            font-weight: bold
        }

        input[type="search"] {
            -webkit-box-sizing: border-box;
            -moz-box-sizing: border-box;
            box-sizing: border-box
        }

        input[type="radio"], input[type="checkbox"] {
            margin: 4px 0 0;
            margin-top: 1px \9;
            line-height: normal
        }

        input[type="file"] {
            display: block
        }

        input[type="range"] {
            display: block;
            width: 100%
        }

        select[multiple], select[size] {
            height: auto
        }

        input[type="file"]:focus, input[type="radio"]:focus, input[type="checkbox"]:focus {
            outline: thin dotted;
            outline: 5px auto -webkit-focus-ring-color;
            outline-offset: -2px
        }

        output {
            display: block;
            padding-top: 7px;
            font-size: 14px;
            line-height: 1.42857143;
            color: #555
        }

        .form-control {
            display: block;
            width: 100%;
            height: 34px;
            padding: 6px 12px;
            font-size: 14px;
            line-height: 1.42857143;
            color: #555;
            background-color: #fff;
            background-image: none;
            border: 1px solid #ccc;
            border-radius: 4px;
            -webkit-box-shadow: inset 0 1px 1px rgba(0, 0, 0, 0.075);
            box-shadow: inset 0 1px 1px rgba(0, 0, 0, 0.075);
            -webkit-transition: border-color ease-in-out .15s, box-shadow ease-in-out .15s;
            -o-transition: border-color ease-in-out .15s, box-shadow ease-in-out .15s;
            transition: border-color ease-in-out .15s, box-shadow ease-in-out .15s
        }

        .form-control:focus {
            border-color: #66afe9;
            outline: 0;
            -webkit-box-shadow: inset 0 1px 1px rgba(0, 0, 0, .075), 0 0 8px rgba(102, 175, 233, 0.6);
            box-shadow: inset 0 1px 1px rgba(0, 0, 0, .075), 0 0 8px rgba(102, 175, 233, 0.6)
        }

        .form-control::-moz-placeholder {
            color: #777;
            opacity: 1
        }

        .form-control:-ms-input-placeholder {
            color: #777
        }

        .form-control::-webkit-input-placeholder {
            color: #777
        }

        .form-control[disabled], .form-control[readonly], fieldset[disabled] .form-control {
            cursor: not-allowed;
            background-color: #eee;
            opacity: 1
        }

        textarea.form-control {
            height: auto
        }

        input[type="search"] {
            -webkit-appearance: none
        }

        input[type="date"], input[type="time"], input[type="datetime-local"], input[type="month"] {
            line-height: 34px;
            line-height: 1.42857143 \0
        }

        input[type="date"].input-sm, input[type="time"].input-sm, input[type="datetime-local"].input-sm, input[type="month"].input-sm {
            line-height: 30px
        }

        input[type="date"].input-lg, input[type="time"].input-lg, input[type="datetime-local"].input-lg, input[type="month"].input-lg {
            line-height: 46px
        }

        .form-group {
            margin-bottom: 15px
        }

        .radio, .checkbox {
            position: relative;
            display: block;
            min-height: 20px;
            margin-top: 10px;
            margin-bottom: 10px
        }

        .radio label, .checkbox label {
            padding-left: 20px;
            margin-bottom: 0;
            font-weight: normal;
            cursor: pointer
        }

        .radio input[type="radio"], .radio-inline input[type="radio"], .checkbox input[type="checkbox"], .checkbox-inline input[type="checkbox"] {
            position: absolute;
            margin-left: -20px;
            margin-top: 4px \9
        }

        .radio + .radio, .checkbox + .checkbox {
            margin-top: -5px
        }

        .radio-inline, .checkbox-inline {
            display: inline-block;
            padding-left: 20px;
            margin-bottom: 0;
            vertical-align: middle;
            font-weight: normal;
            cursor: pointer
        }

        .radio-inline + .radio-inline, .checkbox-inline + .checkbox-inline {
            margin-top: 0;
            margin-left: 10px
        }

        input[type="radio"][disabled], input[type="checkbox"][disabled], input[type="radio"].disabled, input[type="checkbox"].disabled, fieldset[disabled] input[type="radio"], fieldset[disabled] input[type="checkbox"] {
            cursor: not-allowed
        }

        .radio-inline.disabled, .checkbox-inline.disabled, fieldset[disabled] .radio-inline, fieldset[disabled] .checkbox-inline {
            cursor: not-allowed
        }

        .radio.disabled label, .checkbox.disabled label, fieldset[disabled] .radio label, fieldset[disabled] .checkbox label {
            cursor: not-allowed
        }

        .form-control-static {
            padding-top: 7px;
            padding-bottom: 7px;
            margin-bottom: 0
        }

        .form-control-static.input-lg, .form-control-static.input-sm {
            padding-left: 0;
            padding-right: 0
        }

        .input-sm, .form-horizontal .form-group-sm .form-control {
            height: 30px;
            padding: 5px 10px;
            font-size: 12px;
            line-height: 1.5;
            border-radius: 3px
        }

        select.input-sm {
            height: 30px;
            line-height: 30px
        }

        textarea.input-sm, select[multiple].input-sm {
            height: auto
        }

        .input-lg, .form-horizontal .form-group-lg .form-control {
            height: 46px;
            padding: 10px 16px;
            font-size: 18px;
            line-height: 1.33;
            border-radius: 6px
        }

        select.input-lg {
            height: 46px;
            line-height: 46px
        }

        textarea.input-lg, select[multiple].input-lg {
            height: auto
        }

        .has-feedback {
            position: relative
        }

        .has-feedback .form-control {
            padding-right: 42.5px
        }

        .form-control-feedback {
            position: absolute;
            top: 25px;
            right: 0;
            z-index: 2;
            display: block;
            width: 34px;
            height: 34px;
            line-height: 34px;
            text-align: center
        }

        .input-lg + .form-control-feedback {
            width: 46px;
            height: 46px;
            line-height: 46px
        }

        .input-sm + .form-control-feedback {
            width: 30px;
            height: 30px;
            line-height: 30px
        }

        .has-success .help-block, .has-success .control-label, .has-success .radio, .has-success .checkbox, .has-success .radio-inline, .has-success .checkbox-inline {
            color: #3c763d
        }

        .has-success .form-control {
            border-color: #3c763d;
            -webkit-box-shadow: inset 0 1px 1px rgba(0, 0, 0, 0.075);
            box-shadow: inset 0 1px 1px rgba(0, 0, 0, 0.075)
        }

        .has-success .form-control:focus {
            border-color: #2b542c;
            -webkit-box-shadow: inset 0 1px 1px rgba(0, 0, 0, 0.075), 0 0 6px #67b168;
            box-shadow: inset 0 1px 1px rgba(0, 0, 0, 0.075), 0 0 6px #67b168
        }

        .has-success .input-group-addon {
            color: #3c763d;
            border-color: #3c763d;
            background-color: #dff0d8
        }

        .has-success .form-control-feedback {
            color: #3c763d
        }

        .has-warning .help-block, .has-warning .control-label, .has-warning .radio, .has-warning .checkbox, .has-warning .radio-inline, .has-warning .checkbox-inline {
            color: #8a6d3b
        }

        .has-warning .form-control {
            border-color: #8a6d3b;
            -webkit-box-shadow: inset 0 1px 1px rgba(0, 0, 0, 0.075);
            box-shadow: inset 0 1px 1px rgba(0, 0, 0, 0.075)
        }

        .has-warning .form-control:focus {
            border-color: #66512c;
            -webkit-box-shadow: inset 0 1px 1px rgba(0, 0, 0, 0.075), 0 0 6px #c0a16b;
            box-shadow: inset 0 1px 1px rgba(0, 0, 0, 0.075), 0 0 6px #c0a16b
        }

        .has-warning .input-group-addon {
            color: #8a6d3b;
            border-color: #8a6d3b;
            background-color: #fcf8e3
        }

        .has-warning .form-control-feedback {
            color: #8a6d3b
        }

        .has-error .help-block, .has-error .control-label, .has-error .radio, .has-error .checkbox, .has-error .radio-inline, .has-error .checkbox-inline {
            color: #a94442
        }

        .has-error .form-control {
            border-color: #a94442;
            -webkit-box-shadow: inset 0 1px 1px rgba(0, 0, 0, 0.075);
            box-shadow: inset 0 1px 1px rgba(0, 0, 0, 0.075)
        }

        .has-error .form-control:focus {
            border-color: #843534;
            -webkit-box-shadow: inset 0 1px 1px rgba(0, 0, 0, 0.075), 0 0 6px #ce8483;
            box-shadow: inset 0 1px 1px rgba(0, 0, 0, 0.075), 0 0 6px #ce8483
        }

        .has-error .input-group-addon {
            color: #a94442;
            border-color: #a94442;
            background-color: #f2dede
        }

        .has-error .form-control-feedback {
            color: #a94442
        }

        .has-feedback label.sr-only ~ .form-control-feedback {
            top: 0
        }

        .help-block {
            display: block;
            margin-top: 5px;
            margin-bottom: 10px;
            color: #737373
        }

        @media (min-width: 768px) {
            .form-inline .form-group {
                display: inline-block;
                margin-bottom: 0;
                vertical-align: middle
            }

            .form-inline .form-control {
                display: inline-block;
                width: auto;
                vertical-align: middle
            }

            .form-inline .input-group {
                display: inline-table;
                vertical-align: middle
            }

            .form-inline .input-group .input-group-addon, .form-inline .input-group .input-group-btn, .form-inline .input-group .form-control {
                width: auto
            }

            .form-inline .input-group > .form-control {
                width: 100%
            }

            .form-inline .control-label {
                margin-bottom: 0;
                vertical-align: middle
            }

            .form-inline .radio, .form-inline .checkbox {
                display: inline-block;
                margin-top: 0;
                margin-bottom: 0;
                vertical-align: middle
            }

            .form-inline .radio label, .form-inline .checkbox label {
                padding-left: 0
            }

            .form-inline .radio input[type="radio"], .form-inline .checkbox input[type="checkbox"] {
                position: relative;
                margin-left: 0
            }

            .form-inline .has-feedback .form-control-feedback {
                top: 0
            }
        }

        .form-horizontal .radio, .form-horizontal .checkbox, .form-horizontal .radio-inline, .form-horizontal .checkbox-inline {
            margin-top: 0;
            margin-bottom: 0;
            padding-top: 7px
        }

        .form-horizontal .radio, .form-horizontal .checkbox {
            min-height: 27px
        }

        .form-horizontal .form-group {
            margin-left: -15px;
            margin-right: -15px
        }

        @media (min-width: 768px) {
            .form-horizontal .control-label {
                text-align: right;
                margin-bottom: 0;
                padding-top: 7px
            }
        }

        .form-horizontal .has-feedback .form-control-feedback {
            top: 0;
            right: 15px
        }

        @media (min-width: 768px) {
            .form-horizontal .form-group-lg .control-label {
                padding-top: 14.3px
            }
        }

        @media (min-width: 768px) {
            .form-horizontal .form-group-sm .control-label {
                padding-top: 6px
            }
        }

        .btn {
            display: inline-block;
            margin-bottom: 0;
            font-weight: normal;
            text-align: center;
            vertical-align: middle;
            cursor: pointer;
            background-image: none;
            border: 1px solid transparent;
            white-space: nowrap;
            padding: 6px 12px;
            font-size: 14px;
            line-height: 1.42857143;
            border-radius: 4px;
            -webkit-user-select: none;
            -moz-user-select: none;
            -ms-user-select: none;
            user-select: none
        }

        .btn:focus, .btn:active:focus, .btn.active:focus {
            outline: thin dotted;
            outline: 5px auto -webkit-focus-ring-color;
            outline-offset: -2px
        }

        .btn:hover, .btn:focus {
            color: #333;
            text-decoration: none
        }

        .btn:active, .btn.active {
            outline: 0;
            background-image: none;
            -webkit-box-shadow: inset 0 3px 5px rgba(0, 0, 0, 0.125);
            box-shadow: inset 0 3px 5px rgba(0, 0, 0, 0.125)
        }

        .btn.disabled, .btn[disabled], fieldset[disabled] .btn {
            cursor: not-allowed;
            pointer-events: none;
            opacity: .65;
            filter: alpha(opacity=65);
            -webkit-box-shadow: none;
            box-shadow: none
        }

        .btn-default {
            color: #333;
            background-color: #fff;
            border-color: #ccc
        }

        .btn-default:hover, .btn-default:focus, .btn-default:active, .btn-default.active, .open > .dropdown-toggle.btn-default {
            color: #333;
            background-color: #e6e6e6;
            border-color: #adadad
        }

        .btn-default:active, .btn-default.active, .open > .dropdown-toggle.btn-default {
            background-image: none
        }

        .btn-default.disabled, .btn-default[disabled], fieldset[disabled] .btn-default, .btn-default.disabled:hover, .btn-default[disabled]:hover, fieldset[disabled] .btn-default:hover, .btn-default.disabled:focus, .btn-default[disabled]:focus, fieldset[disabled] .btn-default:focus, .btn-default.disabled:active, .btn-default[disabled]:active, fieldset[disabled] .btn-default:active, .btn-default.disabled.active, .btn-default[disabled].active, fieldset[disabled] .btn-default.active {
            background-color: #fff;
            border-color: #ccc
        }

        .btn-default .badge {
            color: #fff;
            background-color: #333
        }

        .btn-primary {
            color: #fff;
            background-color: #428bca;
            border-color: #357ebd
        }

        .btn-primary:hover, .btn-primary:focus, .btn-primary:active, .btn-primary.active, .open > .dropdown-toggle.btn-primary {
            color: #fff;
            background-color: #3071a9;
            border-color: #285e8e
        }

        .btn-primary:active, .btn-primary.active, .open > .dropdown-toggle.btn-primary {
            background-image: none
        }

        .btn-primary.disabled, .btn-primary[disabled], fieldset[disabled] .btn-primary, .btn-primary.disabled:hover, .btn-primary[disabled]:hover, fieldset[disabled] .btn-primary:hover, .btn-primary.disabled:focus, .btn-primary[disabled]:focus, fieldset[disabled] .btn-primary:focus, .btn-primary.disabled:active, .btn-primary[disabled]:active, fieldset[disabled] .btn-primary:active, .btn-primary.disabled.active, .btn-primary[disabled].active, fieldset[disabled] .btn-primary.active {
            background-color: #428bca;
            border-color: #357ebd
        }

        .btn-primary .badge {
            color: #428bca;
            background-color: #fff
        }

        .btn-success {
            color: #fff;
            background-color: #5cb85c;
            border-color: #4cae4c
        }

        .btn-success:hover, .btn-success:focus, .btn-success:active, .btn-success.active, .open > .dropdown-toggle.btn-success {
            color: #fff;
            background-color: #449d44;
            border-color: #398439
        }

        .btn-success:active, .btn-success.active, .open > .dropdown-toggle.btn-success {
            background-image: none
        }

        .btn-success.disabled, .btn-success[disabled], fieldset[disabled] .btn-success, .btn-success.disabled:hover, .btn-success[disabled]:hover, fieldset[disabled] .btn-success:hover, .btn-success.disabled:focus, .btn-success[disabled]:focus, fieldset[disabled] .btn-success:focus, .btn-success.disabled:active, .btn-success[disabled]:active, fieldset[disabled] .btn-success:active, .btn-success.disabled.active, .btn-success[disabled].active, fieldset[disabled] .btn-success.active {
            background-color: #5cb85c;
            border-color: #4cae4c
        }

        .btn-success .badge {
            color: #5cb85c;
            background-color: #fff
        }

        .btn-info {
            color: #fff;
            background-color: #5bc0de;
            border-color: #46b8da
        }

        .btn-info:hover, .btn-info:focus, .btn-info:active, .btn-info.active, .open > .dropdown-toggle.btn-info {
            color: #fff;
            background-color: #31b0d5;
            border-color: #269abc
        }

        .btn-info:active, .btn-info.active, .open > .dropdown-toggle.btn-info {
            background-image: none
        }

        .btn-info.disabled, .btn-info[disabled], fieldset[disabled] .btn-info, .btn-info.disabled:hover, .btn-info[disabled]:hover, fieldset[disabled] .btn-info:hover, .btn-info.disabled:focus, .btn-info[disabled]:focus, fieldset[disabled] .btn-info:focus, .btn-info.disabled:active, .btn-info[disabled]:active, fieldset[disabled] .btn-info:active, .btn-info.disabled.active, .btn-info[disabled].active, fieldset[disabled] .btn-info.active {
            background-color: #5bc0de;
            border-color: #46b8da
        }

        .btn-info .badge {
            color: #5bc0de;
            background-color: #fff
        }

        .btn-warning {
            color: #fff;
            background-color: #f0ad4e;
            border-color: #eea236
        }

        .btn-warning:hover, .btn-warning:focus, .btn-warning:active, .btn-warning.active, .open > .dropdown-toggle.btn-warning {
            color: #fff;
            background-color: #ec971f;
            border-color: #d58512
        }

        .btn-warning:active, .btn-warning.active, .open > .dropdown-toggle.btn-warning {
            background-image: none
        }

        .btn-warning.disabled, .btn-warning[disabled], fieldset[disabled] .btn-warning, .btn-warning.disabled:hover, .btn-warning[disabled]:hover, fieldset[disabled] .btn-warning:hover, .btn-warning.disabled:focus, .btn-warning[disabled]:focus, fieldset[disabled] .btn-warning:focus, .btn-warning.disabled:active, .btn-warning[disabled]:active, fieldset[disabled] .btn-warning:active, .btn-warning.disabled.active, .btn-warning[disabled].active, fieldset[disabled] .btn-warning.active {
            background-color: #f0ad4e;
            border-color: #eea236
        }

        .btn-warning .badge {
            color: #f0ad4e;
            background-color: #fff
        }

        .btn-danger {
            color: #fff;
            background-color: #d9534f;
            border-color: #d43f3a
        }

        .btn-danger:hover, .btn-danger:focus, .btn-danger:active, .btn-danger.active, .open > .dropdown-toggle.btn-danger {
            color: #fff;
            background-color: #c9302c;
            border-color: #ac2925
        }

        .btn-danger:active, .btn-danger.active, .open > .dropdown-toggle.btn-danger {
            background-image: none
        }

        .btn-danger.disabled, .btn-danger[disabled], fieldset[disabled] .btn-danger, .btn-danger.disabled:hover, .btn-danger[disabled]:hover, fieldset[disabled] .btn-danger:hover, .btn-danger.disabled:focus, .btn-danger[disabled]:focus, fieldset[disabled] .btn-danger:focus, .btn-danger.disabled:active, .btn-danger[disabled]:active, fieldset[disabled] .btn-danger:active, .btn-danger.disabled.active, .btn-danger[disabled].active, fieldset[disabled] .btn-danger.active {
            background-color: #d9534f;
            border-color: #d43f3a
        }

        .btn-danger .badge {
            color: #d9534f;
            background-color: #fff
        }

        .btn-link {
            color: #428bca;
            font-weight: normal;
            cursor: pointer;
            border-radius: 0
        }

        .btn-link, .btn-link:active, .btn-link[disabled], fieldset[disabled] .btn-link {
            background-color: transparent;
            -webkit-box-shadow: none;
            box-shadow: none
        }

        .btn-link, .btn-link:hover, .btn-link:focus, .btn-link:active {
            border-color: transparent
        }

        .btn-link:hover, .btn-link:focus {
            color: #2a6496;
            text-decoration: underline;
            background-color: transparent
        }

        .btn-link[disabled]:hover, fieldset[disabled] .btn-link:hover, .btn-link[disabled]:focus, fieldset[disabled] .btn-link:focus {
            color: #777;
            text-decoration: none
        }

        .btn-lg {
            padding: 10px 16px;
            font-size: 18px;
            line-height: 1.33;
            border-radius: 6px
        }

        .btn-sm {
            padding: 5px 10px;
            font-size: 12px;
            line-height: 1.5;
            border-radius: 3px
        }

        .btn-xs {
            padding: 1px 5px;
            font-size: 12px;
            line-height: 1.5;
            border-radius: 3px
        }

        .btn-block {
            display: block;
            width: 100%
        }

        .btn-block + .btn-block {
            margin-top: 5px
        }

        input[type="submit"].btn-block, input[type="reset"].btn-block, input[type="button"].btn-block {
            width: 100%
        }

        .clearfix:before, .clearfix:after, .dl-horizontal dd:before, .dl-horizontal dd:after, .container:before, .container:after, .container-fluid:before, .container-fluid:after, .row:before, .row:after, .form-horizontal .form-group:before, .form-horizontal .form-group:after {
            content: " ";
            display: table
        }

        .clearfix:after, .dl-horizontal dd:after, .container:after, .container-fluid:after, .row:after, .form-horizontal .form-group:after {
            clear: both
        }

        .center-block {
            display: block;
            margin-left: auto;
            margin-right: auto
        }

        .pull-right {
            float: right !important
        }

        .pull-left {
            float: left !important
        }

        .hide {
            display: none !important
        }

        .show {
            display: block !important
        }

        .invisible {
            visibility: hidden
        }

        .text-hide {
            font: 0/0 a;
            color: transparent;
            text-shadow: none;
            background-color: transparent;
            border: 0
        }

        .hidden {
            display: none !important;
            visibility: hidden !important
        }

        .affix {
            position: fixed;
            -webkit-transform: translate3d(0, 0, 0);
            transform: translate3d(0, 0, 0)
        }

        @-ms-viewport {
            width: device-width
        }

        .visible-xs, .visible-sm, .visible-md, .visible-lg {
            display: none !important
        }

        .visible-xs-block, .visible-xs-inline, .visible-xs-inline-block, .visible-sm-block, .visible-sm-inline, .visible-sm-inline-block, .visible-md-block, .visible-md-inline, .visible-md-inline-block, .visible-lg-block, .visible-lg-inline, .visible-lg-inline-block {
            display: none !important
        }

        @media (max-width: 767px) {
            .visible-xs {
                display: block !important
            }

            table.visible-xs {
                display: table
            }

            tr.visible-xs {
                display: table-row !important
            }

            th.visible-xs, td.visible-xs {
                display: table-cell !important
            }
        }

        @media (max-width: 767px) {
            .visible-xs-block {
                display: block !important
            }
        }

        @media (max-width: 767px) {
            .visible-xs-inline {
                display: inline !important
            }
        }

        @media (max-width: 767px) {
            .visible-xs-inline-block {
                display: inline-block !important
            }
        }

        @media (min-width: 768px) and (max-width: 991px) {
            .visible-sm {
                display: block !important
            }

            table.visible-sm {
                display: table
            }

            tr.visible-sm {
                display: table-row !important
            }

            th.visible-sm, td.visible-sm {
                display: table-cell !important
            }
        }

        @media (min-width: 768px) and (max-width: 991px) {
            .visible-sm-block {
                display: block !important
            }
        }

        @media (min-width: 768px) and (max-width: 991px) {
            .visible-sm-inline {
                display: inline !important
            }
        }

        @media (min-width: 768px) and (max-width: 991px) {
            .visible-sm-inline-block {
                display: inline-block !important
            }
        }

        @media (min-width: 992px) and (max-width: 1199px) {
            .visible-md {
                display: block !important
            }

            table.visible-md {
                display: table
            }

            tr.visible-md {
                display: table-row !important
            }

            th.visible-md, td.visible-md {
                display: table-cell !important
            }
        }

        @media (min-width: 992px) and (max-width: 1199px) {
            .visible-md-block {
                display: block !important
            }
        }

        @media (min-width: 992px) and (max-width: 1199px) {
            .visible-md-inline {
                display: inline !important
            }
        }

        @media (min-width: 992px) and (max-width: 1199px) {
            .visible-md-inline-block {
                display: inline-block !important
            }
        }

        @media (min-width: 1200px) {
            .visible-lg {
                display: block !important
            }

            table.visible-lg {
                display: table
            }

            tr.visible-lg {
                display: table-row !important
            }

            th.visible-lg, td.visible-lg {
                display: table-cell !important
            }
        }

        @media (min-width: 1200px) {
            .visible-lg-block {
                display: block !important
            }
        }

        @media (min-width: 1200px) {
            .visible-lg-inline {
                display: inline !important
            }
        }

        @media (min-width: 1200px) {
            .visible-lg-inline-block {
                display: inline-block !important
            }
        }

        @media (max-width: 767px) {
            .hidden-xs {
                display: none !important
            }
        }

        @media (min-width: 768px) and (max-width: 991px) {
            .hidden-sm {
                display: none !important
            }
        }

        @media (min-width: 992px) and (max-width: 1199px) {
            .hidden-md {
                display: none !important
            }
        }

        @media (min-width: 1200px) {
            .hidden-lg {
                display: none !important
            }
        }

        .visible-print {
            display: none !important
        }

        @media print {
            .visible-print {
                display: block !important
            }

            table.visible-print {
                display: table
            }

            tr.visible-print {
                display: table-row !important
            }

            th.visible-print, td.visible-print {
                display: table-cell !important
            }
        }

        .visible-print-block {
            display: none !important
        }

        @media print {
            .visible-print-block {
                display: block !important
            }
        }

        .visible-print-inline {
            display: none !important
        }

        @media print {
            .visible-print-inline {
                display: inline !important
            }
        }

        .visible-print-inline-block {
            display: none !important
        }

        @media print {
            .visible-print-inline-block {
                display: inline-block !important
            }
        }

        @media print {
            .hidden-print {
                display: none !important
            }
        }
    </style>

    <h4>
        项目名：&nbsp;${domain}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;告警时间：&nbsp;${dateStr}
    </h4>

    <table border="1" class="table table-bordered table-striped table-hover">
        <thead>
        <tr>
            <th>告警类型&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</th>
            <th colspan="5">详细警告信息</th>
        </tr>
        </thead>

        <tbody>
        <#list categories?keys as cateName>
            <#if cateName != "long_call">
                <#assign cate = categories[cateName] />
                <#assign length = cate?size />
                <#if length == 0>
                    <tr>
                        <td class="text-success"><strong>${cateName}</strong></td>
                        <td class="text-success" colspan="5"><strong>状况正常</strong></td>
                    </tr>
                <#else>
                    <tr>
                        <td class="text-success" rowspan="${length + 1}"><strong>${cateName}</strong></td>
                        <th>告警指标</th>
                        <th>告警次数</th>
                        <th>告警时间</th>
                        <th>告警级别</th>
                        <th>告警内容</th>
                    </tr>
                    <#list cate as item>
                        <tr>
                            <td class="metric">${item.metric}</td>
                            <td>${item.count}</td>
                            <td>${item.dateStr}</td>
                            <td>${item.type}</td>
                            <td class="alert-content">${item.context}</td>
                        </tr>
                    </#list>
                </#if>
            <#else>
                <#if long_call_length == 0>
                    <tr>
                        <td class="text-success"><strong>超时依赖调用</strong></td>
                        <td class="text-success" colspan="5"><strong>状况正常</strong></td>
                    </tr>
                <#else>
                    <tr>
                        <td class="text-success" rowspan="${long_call_length + 1}"><strong>超时依赖调用</strong></td>
                        <th>依赖项目</th>
                        <th>告警指标</th>
                        <th>告警时间</th>
                        <th>告警级别</th>
                        <th>告警内容</th>
                    </tr>
                    <#list categories.long_call?keys as key>
                        <#list categories.long_call[key] as value>
                            <tr>
                                <#if value_index == 0>
                                    <td rowspan="${categories.long_call[key]?size}">${key}</td>
                                </#if>
                                <td>${value.metric}</td>
                                <td>${value.dateStr}</td>
                                <td>${value.type}</td>
                                <td class="alert-content">${value.context}</td>
                            </tr>
                        </#list>
                    </#list>
                </#if>
            </#if>
        </#list>
        </tbody>

    </table>
</div>
<br/>