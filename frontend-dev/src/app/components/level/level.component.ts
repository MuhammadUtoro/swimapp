import { Component } from '@angular/core';
import { MatExpansionModule } from '@angular/material/expansion';
import { MatListModule } from '@angular/material/list';
import { CommonModule } from '@angular/common';
import { LevelService, LevelDTO } from '../../services/level.service';

@Component({
  selector: 'app-level',
  standalone: true,
  imports: [CommonModule, MatExpansionModule, MatListModule],
  templateUrl: './level.component.html',
  styleUrl: './level.component.scss',
})
export class LevelComponent {
  // We have to declare our data from the backend
  levels: LevelDTO[] = [];

  constructor(private levelService: LevelService) {}

  ngOnInit(): void {
    this.levelService.getAllLevels().subscribe({
      next: (data) => (this.levels = data),
      error: (err) => console.error('Error loading levels'),
    });
  }
}
