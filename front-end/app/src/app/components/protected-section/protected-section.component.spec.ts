import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProtectedSectionComponent } from './protected-section.component';

describe('ProtectedSectionComponent', () => {
  let component: ProtectedSectionComponent;
  let fixture: ComponentFixture<ProtectedSectionComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ProtectedSectionComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ProtectedSectionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
