import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PopupQueryFormComponent } from './popup-query-form.component';

describe('PopupQueryFormComponent', () => {
  let component: PopupQueryFormComponent;
  let fixture: ComponentFixture<PopupQueryFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [PopupQueryFormComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(PopupQueryFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
