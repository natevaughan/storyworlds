var clean = require('gulp-clean');
var gulp = require('gulp');
var less = require('gulp-less');
var uglify = require('gulp-uglify');
var debug = require('gulp-debug');
var concat = require('gulp-concat');
var templatecache = require('gulp-angular-templatecache');

var components = {
    src : {
        base : 'src/main/web/',
        app : 'app/',
        vendor : 'vendor/'
    },
    dest : {
        target : 'src/main/resources/static/',
        vendor : 'vendor/',
        js : {
            dir : 'js/',
            file : 'app.js',
            templatecache : 'templates.js'
        },
        css : {
            dir : 'css/',
            file : 'styles.css'
        },
        img : {
            dir : 'img/'
        }
    },
    index : 'index.html'
};

var paths = {
    app : components.src.base + components.src.app,
    vendor : components.src.base + components.src.vendor,
    index : components.src.base + components.index
};

gulp.task('default', ['css'], function() {
})

gulp.task('clean', [], function() {
    console.log('wipe.');
    return gulp.src(components.dest.target + '**/*')
        .pipe(debug())
        .pipe(clean());
})

gulp.task('static', ['clean'], function() {
    gulp.src([paths.vendor + '**/*.js'])
            .pipe(debug())
            .pipe(gulp.dest(components.dest.target + components.dest.vendor))

    gulp.src([paths.app + '**/*.jpg'])
            .pipe(debug())
            .pipe(gulp.dest(components.dest.target + components.dest.img.dir))

    return gulp.src(paths.index)
        .pipe(debug())
        .pipe(gulp.dest(components.dest.target));
})

gulp.task('js', ['static'], function() {
    return gulp.src([paths.app + '/**/*.js',
        '!' + paths.app + '**/*.spec.js'])
        .pipe(debug())
        .pipe(concat(components.dest.js.file))
        .pipe(uglify())
        .pipe(gulp.dest(components.dest.target + components.dest.js.dir));
})

gulp.task('templatecache', ['js'], function() {

    gulp.src([paths.app + '**/*.html'])
        .pipe(debug())
        .pipe(templatecache(components.dest.js.templatecache, {standalone: true}))
        .pipe(uglify())
        .pipe(gulp.dest(components.dest.target + components.dest.js.dir));
})

gulp.task('css', ['templatecache'], function() {
    return gulp.src([paths.app + '**/*.css', paths.app + '**/*.less'])
        .pipe(less())
        .pipe(concat(components.dest.css.file))
        .pipe(gulp.dest(components.dest.target + components.dest.css.dir));
})