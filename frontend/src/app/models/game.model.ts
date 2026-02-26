import { Achievement } from "./achievement.model";

export interface Game {
    apiId: number; // Steam game id
    name: string;
    totalAchievements: number;
    capsuleImage: string;
    capsuleImageV5: string;
    headerImage?: string;
    detailedDescription?: string;
    shortDescription?: string;
    developers?: string[];
    publishers?: string[];
    categories?: Category[];
    genres?: Genre[];
    comingSoon?: boolean;
    date?: string;
    achievements?: Achievement[];

    isFullLoaded?: boolean; // Check if the Game information is complete or not
}

export interface Genre {
    apiId: number;
    description: string;
}

export interface Category {
    apiId: number;
    description: string;
}

export interface GamesPageResponse {
  content: Game[];
  page: {
    size: number;
    number: number;
    totalElements: number;
    totalPages: number;
  };
}