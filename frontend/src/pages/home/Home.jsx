import React from 'react';
import { FaFire, FaStar, FaUsers, FaChartBar, FaArrowRight, FaPlayCircle } from 'react-icons/fa';
import { Link } from 'react-router-dom';

const HomePage = () => {
    return (
        <div className="min-h-screen bg-gradient-to-b from-gray-50 to-white">
            <div className="relative overflow-hidden">
                <div className="absolute inset-0 bg-gradient-to-r from-blue-600/10 to-purple-600/10"></div>

                <div className="container mx-auto px-4 py-20 md:py-32 relative z-10">
                    <div className="max-w-3xl mx-auto text-center">
                        <h1 className="text-4xl md:text-6xl font-bold text-gray-900 mb-6">
                            Créez, Partagez et Découvrez des
                            <span className="bg-gradient-to-r from-blue-600 to-purple-600 bg-clip-text text-transparent"> Tier Lists</span>
                        </h1>

                        <p className="text-xl text-gray-600 mb-10 max-w-2xl mx-auto">
                            La plateforme ultime pour classer tout ce que vous aimez. Des jeux vidéo aux films, en passant par la musique et bien plus encore.
                        </p>

                        <div className="flex flex-col sm:flex-row gap-4 justify-center">
                            <Link
                                to="/tierlist/new"
                                className="btn btn-primary btn-lg rounded-xl px-8 py-4 text-lg font-semibold shadow-lg hover:shadow-xl transition-all duration-300"
                            >
                                <FaPlayCircle className="mr-2" />
                                Créer une Tier List
                            </Link>

                            <Link
                                to="/tierlist/explore"
                                className="btn btn-outline btn-primary btn-lg rounded-xl px-8 py-4 text-lg font-semibold"
                            >
                                Explorer
                                <FaArrowRight className="ml-2" />
                            </Link>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
};

export default HomePage;