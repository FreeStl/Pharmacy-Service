import { TestBed } from '@angular/core/testing';

import { ComplicatedQueriesService } from './complicated-queries.service';

describe('ComplicatedQueriesService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: ComplicatedQueriesService = TestBed.get(ComplicatedQueriesService);
    expect(service).toBeTruthy();
  });
});
