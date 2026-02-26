import { Component, Input } from '@angular/core';
import { Achievement, AchievementProgress } from '../../../models/achievement.model';

@Component({
  selector: 'app-achievement-card-listed',
  imports: [],
  templateUrl: './achievement-card-listed.html',
  styleUrl: './achievement-card-listed.scss',
})
export class AchievementCardListed {
  // Attributes received from the parent
  @Input() achievement!: Achievement; 
  @Input() achievementProgress!: AchievementProgress; 
}
