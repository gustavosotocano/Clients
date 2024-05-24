import { ComponentFixture, TestBed } from '@angular/core/testing';

import { JsoncvsComponent } from './jsoncvs.component';

describe('JsoncvsComponent', () => {
  let component: JsoncvsComponent;
  let fixture: ComponentFixture<JsoncvsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [JsoncvsComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(JsoncvsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
