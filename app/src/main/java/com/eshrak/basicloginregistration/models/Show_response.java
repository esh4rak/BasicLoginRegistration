package com.eshrak.basicloginregistration.models;


public class Show_response {

    private String score;

    private Show show;

    public String getScore() {
        return score;
    }

    public Show getShow() {
        return show;
    }

    public class Show {
        private String summary;

        private Image image;

        private String averageRuntime;

        private String dvdCountry;

        private String premiered;

        private Rating rating;

        private String runtime;

        private String language;

        private String type;

        private String url;

        private String officialSite;

        private String[] genres;

        private String name;

        private String ended;

        private String id;


        private String updated;

        private String status;

        public String getSummary() {
            return summary;
        }

        public Image getImage() {
            return image;
        }

        public String getAverageRuntime() {
            return averageRuntime;
        }

        public String getDvdCountry() {
            return dvdCountry;
        }

        public String getPremiered() {
            return premiered;
        }

        public Rating getRating() {
            return rating;
        }

        public String getRuntime() {
            return runtime;
        }

        public String getLanguage() {
            return language;
        }

        public String getType() {
            return type;
        }

        public String getUrl() {
            return url;
        }

        public String getOfficialSite() {
            return officialSite;
        }

        public String[] getGenres() {
            return genres;
        }

        public String getName() {
            return name;
        }

        public String getEnded() {
            return ended;
        }

        public String getId() {
            return id;
        }

        public String getUpdated() {
            return updated;
        }

        public String getStatus() {
            return status;
        }

        public class Image {
            private String original;

            private String medium;

            public String getOriginal() {
                return original;
            }

            public String getMedium() {
                return medium;
            }
        }

        public class Rating {
            private String average;

            public String getAverage() {
                return average;
            }
        }


    }


}
