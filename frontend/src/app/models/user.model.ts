import { Achievement, AchievementProgress } from "./achievement.model";

export interface User {
    username: string;
    email: string;
    role: string;
    enabled: boolean;
    level?: number;
    platinums?: number;
    steamId?: string;
    personaName?: string;
    profileUrl?: string;
    avatar?: string;
    avatarMedium?: string;
    avatarFull?: string;
    communityVisibilityState?: number;
    profileState?: number;
    timeCreated?: number;
    achievements?: Map<number, AchievementProgress[]>
}

export interface RegisterRequest {
    username: string;
    password: string;
    email: string;
}

export interface MessageResponse {
    message: string;
}

export interface LoginRequest {
    username: string;
    password: string;
}

export interface LoginResponse {
    token: string;
    type: string;
    username: string;
    email: string;
    role: string;
    enabled: boolean;
}