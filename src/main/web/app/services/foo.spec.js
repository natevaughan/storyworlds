"use strict";

describe('demoService', function() {
    var demoService;

    beforeEach(module('storyworlds.demoService'));

    beforeEach(inject(function(_demoService_) {
        demoService = _demoService_;
    }))

    it('should add', function () {
        expect(demoService.add(1)).toEqual(2);
    });
});