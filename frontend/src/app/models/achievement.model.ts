export interface Achievement {
    name: string;
    displayName: string;
    description: string;
    hidden: boolean;
    icon: string;
    iconGray: string;
    playerPercentUnlocked: number;
}

export interface AchievementGroup {
    gameApiId: number;
    achievements: Achievement[];
}

export interface AchievementProgress {
    name: string;
    displayName: string;
    description: string;
    hidden: boolean;
    icon: string;
    iconGray: string;
    playerPercentUnlocked: number;
    unlocked: boolean;
}

export interface AchievementProgressGroup {
    gameApiId: number;
    achievements: AchievementProgress[];
}