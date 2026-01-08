import React, { useState, useEffect } from 'react';
import {
    FaSearch, FaFilter, FaPlus, FaEdit, FaTrash, FaShare, FaEye,
    FaStar, FaLock, FaGlobe, FaUsers, FaCalendar, FaSort,
    FaEllipsisV, FaCopy, FaDownload, FaArchive, FaChartBar
} from 'react-icons/fa';
import { Link, useNavigate } from 'react-router-dom';
import config from "../../api/apiConfig.js";
import {useAuth} from "../../AuthProvider.jsx";

const MyTierListsPage = () => {
    const [searchQuery, setSearchQuery] = useState('');
    const [filterStatus, setFilterStatus] = useState('all');
    const [sortBy, setSortBy] = useState('recent');
    const [selectedLists, setSelectedLists] = useState([]);
    const [showDeleteModal, setShowDeleteModal] = useState(false);
    const [showShareModal, setShowShareModal] = useState(false);
    const [activeListId, setActiveListId] = useState(null);
    const {currentUser} = useAuth();

    useEffect(() => {
        fetchTierLists()
    }, []);

    const fetchTierLists = async () => {
        const response = await fetch(`${config.apiBaseUrl}/tier-lists/creator/${currentUser.id}`, {
            headers: config.getHeaders(),
        });

        const data = await response.json();

        console.log(data);
    }

    const [tierLists, setTierLists] = useState([
        {
            id: 1,
            title: "Top Films 2024",
            description: "Mon classement des meilleurs films de l'année",
            category: "Films",
            createdAt: "2024-01-15",
            updatedAt: "2024-02-20",
            itemsCount: 25,
            likes: 156,
            views: 1245,
            privacy: "public",
            thumbnail: "https://picsum.photos/seed/film1/400/250",
            isFeatured: true,
            isDraft: false
        },
        {
            id: 2,
            title: "Jeux PS5 Essentiels",
            description: "Les jeux à absolument avoir sur PS5",
            category: "Jeux Vidéo",
            createdAt: "2024-01-10",
            updatedAt: "2024-02-18",
            itemsCount: 15,
            likes: 89,
            views: 567,
            privacy: "public",
            thumbnail: "https://picsum.photos/seed/game1/400/250",
            isFeatured: false,
            isDraft: false
        },
        {
            id: 3,
            title: "Restaurants Paris",
            description: "Mes adresses préférées à Paris",
            category: "Nourriture",
            createdAt: "2024-02-01",
            updatedAt: "2024-02-01",
            itemsCount: 30,
            likes: 45,
            views: 234,
            privacy: "private",
            thumbnail: "https://picsum.photos/seed/food1/400/250",
            isFeatured: false,
            isDraft: false
        },
        {
            id: 4,
            title: "Artistes Hip-Hop 2024",
            description: "Classement en cours de création",
            category: "Musique",
            createdAt: "2024-02-25",
            updatedAt: "2024-02-25",
            itemsCount: 8,
            likes: 0,
            views: 0,
            privacy: "private",
            thumbnail: null,
            isFeatured: false,
            isDraft: true
        },
        {
            id: 5,
            title: "Marques de Smartphones",
            description: "Comparaison des meilleures marques",
            category: "Technologie",
            createdAt: "2024-01-05",
            updatedAt: "2024-02-10",
            itemsCount: 12,
            likes: 67,
            views: 456,
            privacy: "public",
            thumbnail: "https://picsum.photos/seed/tech1/400/250",
            isFeatured: true,
            isDraft: false
        },
        {
            id: 6,
            title: "Séries Netflix à voir",
            description: "Ma watchlist personnelle",
            category: "Séries",
            createdAt: "2024-02-15",
            updatedAt: "2024-02-20",
            itemsCount: 20,
            likes: 34,
            views: 189,
            privacy: "shared",
            thumbnail: "https://picsum.photos/seed/series1/400/250",
            isFeatured: false,
            isDraft: false
        }
    ]);

    const filteredLists = tierLists
        .filter(list => {
            const matchesSearch =
                list.title.toLowerCase().includes(searchQuery.toLowerCase()) ||
                list.description.toLowerCase().includes(searchQuery.toLowerCase()) ||
                list.category.toLowerCase().includes(searchQuery.toLowerCase());

            const matchesFilter =
                filterStatus === 'all'

            return matchesSearch && matchesFilter;
        })
        .sort((a, b) => {
            switch(sortBy) {
                case 'recent':
                    return new Date(b.updatedAt) - new Date(a.updatedAt);
                case 'oldest':
                    return new Date(a.updatedAt) - new Date(b.updatedAt);
                case 'popular':
                    return b.likes - a.likes;
                case 'views':
                    return b.views - a.views;
                case 'title':
                    return a.title.localeCompare(b.title);
                default:
                    return 0;
            }
        });

    const toggleSelectList = (id) => {
        setSelectedLists(prev =>
            prev.includes(id)
                ? prev.filter(listId => listId !== id)
                : [...prev, id]
        );
    };

    const handleDelete = (id) => {
        if (id) {
            setTierLists(prev => prev.filter(list => list.id !== id));
            setSelectedLists(prev => prev.filter(listId => listId !== id));
        } else {
            setTierLists(prev => prev.filter(list => !selectedLists.includes(list.id)));
            setSelectedLists([]);
        }
        setShowDeleteModal(false);
    };

    const handleChangePrivacy = (id, privacy) => {
        setTierLists(prev => prev.map(list =>
            list.id === id ? { ...list, privacy } : list
        ));
    };

    const handleExport = (id) => {
        const list = tierLists.find(l => l.id === id);
        const data = JSON.stringify(list, null, 2);
        const blob = new Blob([data], { type: 'application/json' });
        const url = URL.createObjectURL(blob);
        const a = document.createElement('a');
        a.href = url;
        a.download = `${list.title.replace(/\s+/g, '_')}.json`;
        a.click();
        URL.revokeObjectURL(url);
    };

    const DeleteModal = () => (
        <div className="modal modal-open">
            <div className="modal-box">
                <h3 className="font-bold text-lg">
                    {activeListId ? "Supprimer cette tier list ?" : `Supprimer ${selectedLists.length} tier list(s) ?`}
                </h3>
                <p className="py-4">
                    Cette action est irréversible. Toutes les données seront perdues.
                </p>
                <div className="modal-action">
                    <button
                        className="btn btn-ghost"
                        onClick={() => {
                            setShowDeleteModal(false);
                            setActiveListId(null);
                        }}
                    >
                        Annuler
                    </button>
                    <button
                        className="btn btn-error"
                        onClick={() => handleDelete(activeListId)}
                    >
                        Supprimer
                    </button>
                </div>
            </div>
        </div>
    );

    const ShareModal = () => {
        const list = tierLists.find(l => l.id === activeListId);
        const shareUrl = `${window.location.origin}/tierlist/${activeListId}`;

        return (
            <div className="modal modal-open">
                <div className="modal-box">
                    <h3 className="font-bold text-lg">Partager "{list?.title}"</h3>

                    <div className="space-y-4 py-4">
                        <div>
                            <label className="label">
                                <span className="label-text">Lien de partage</span>
                            </label>
                            <div className="flex gap-2">
                                <input
                                    type="text"
                                    value={shareUrl}
                                    readOnly
                                    className="input input-bordered flex-1"
                                />
                                <button
                                    className="btn btn-primary"
                                    onClick={() => {
                                        navigator.clipboard.writeText(shareUrl);
                                        alert('Lien copié !');
                                    }}
                                >
                                    Copier
                                </button>
                            </div>
                        </div>

                        <div className="divider">Ou</div>

                        <div className="flex gap-2 justify-center">
                            <button className="btn btn-outline">
                                Facebook
                            </button>
                            <button className="btn btn-outline">
                                Twitter
                            </button>
                            <button className="btn btn-outline">
                                Copier le lien
                            </button>
                        </div>
                    </div>

                    <div className="modal-action">
                        <button
                            className="btn"
                            onClick={() => {
                                setShowShareModal(false);
                                setActiveListId(null);
                            }}
                        >
                            Fermer
                        </button>
                    </div>
                </div>
            </div>
        );
    };

    return (
        <div className="min-h-screen bg-gradient-to-b from-gray-50 to-white">
            <div className="bg-white border-b">
                <div className="container mx-auto px-4 py-8">
                    <div className="flex flex-col md:flex-row justify-between items-start md:items-center gap-4">
                        <div>
                            <h1 className="text-3xl font-bold text-gray-900">Mes Tier Lists</h1>
                            <p className="text-gray-600 mt-2">
                                Gérez et organisez toutes vos créations
                            </p>
                        </div>

                        <Link
                            to="/tierlist/new"
                            className="btn btn-primary gap-2 rounded-xl px-6 hover:shadow-lg transition-shadow"
                        >
                            <FaPlus />
                            Nouvelle Tier List
                        </Link>
                    </div>
                </div>
            </div>

            <div className="container mx-auto px-4 py-8">

                <div className="bg-white rounded-2xl shadow-sm border p-4 mb-6">
                    <div className="flex flex-col md:flex-row gap-4 justify-between items-center">
                        <div className="relative flex-1 w-full md:w-auto">
                            <div className="absolute inset-y-0 left-0 pl-3 flex items-center pointer-events-none">
                                <FaSearch className="text-gray-400" />
                            </div>
                            <input
                                type="text"
                                value={searchQuery}
                                onChange={(e) => setSearchQuery(e.target.value)}
                                placeholder="Rechercher dans mes tier lists..."
                                className="input input-bordered w-full pl-10"
                            />
                        </div>

                        <div className="flex gap-2 w-full md:w-auto">
                            <div className="dropdown">
                                <label tabIndex={0} className="btn btn-outline gap-2">
                                    <FaFilter />
                                    {filterStatus === 'all' ? 'Tous' :
                                        filterStatus === 'draft' ? 'Brouillons' :
                                            filterStatus === 'public' ? 'Public' :
                                                filterStatus === 'private' ? 'Privé' : 'En vedette'}
                                </label>
                                <ul tabIndex={0} className="dropdown-content menu p-2 shadow bg-base-100 rounded-box w-52">
                                    <li><button onClick={() => setFilterStatus('all')}>Tous</button></li>
                                    <li><button onClick={() => setFilterStatus('public')}>Publiques</button></li>
                                    <li><button onClick={() => setFilterStatus('private')}>Privées</button></li>
                                    <li><button onClick={() => setFilterStatus('draft')}>Brouillons</button></li>
                                    <li><button onClick={() => setFilterStatus('featured')}>En vedette</button></li>
                                </ul>
                            </div>

                            <div className="dropdown">
                                <label tabIndex={0} className="btn btn-outline gap-2">
                                    <FaSort />
                                    Trier
                                </label>
                                <ul tabIndex={0} className="dropdown-content menu p-2 shadow bg-base-100 rounded-box w-52">
                                    <li><button onClick={() => setSortBy('recent')}>Plus récent</button></li>
                                    <li><button onClick={() => setSortBy('oldest')}>Plus ancien</button></li>
                                    <li><button onClick={() => setSortBy('popular')}>Plus populaire</button></li>
                                    <li><button onClick={() => setSortBy('views')}>Plus de vues</button></li>
                                    <li><button onClick={() => setSortBy('title')}>Par titre</button></li>
                                </ul>
                            </div>
                        </div>
                    </div>

                    {selectedLists.length > 0 && (
                        <div className="mt-4 p-3 bg-blue-50 rounded-lg flex flex-wrap gap-2 items-center">
                            <span className="font-semibold">
                                {selectedLists.length} sélectionné(s)
                            </span>
                            <div className="divider divider-horizontal mx-2"></div>
                            <button
                                className="btn btn-sm btn-error gap-2"
                                onClick={() => setShowDeleteModal(true)}
                            >
                                <FaTrash />
                                Supprimer
                            </button>
                            <button className="btn btn-sm btn-outline gap-2">
                                <FaShare />
                                Partager
                            </button>
                            <button className="btn btn-sm btn-outline gap-2">
                                <FaArchive />
                                Archiver
                            </button>
                            <button
                                className="btn btn-sm btn-ghost ml-auto"
                                onClick={() => setSelectedLists([])}
                            >
                                Tout désélectionner
                            </button>
                        </div>
                    )}
                </div>

                {filteredLists.length > 0 ? (
                    <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-6">
                        {filteredLists.map((list) => (
                            <div
                                key={list.id}
                                className={`bg-white rounded-2xl shadow-sm border overflow-hidden hover:shadow-lg transition-all duration-300 ${
                                    selectedLists.includes(list.id) ? 'ring-2 ring-blue-500' : ''
                                }`}
                            >
                                <div className="p-4 border-b flex justify-between items-start">
                                    <div className="flex items-start gap-3">
                                        <input
                                            type="checkbox"
                                            checked={selectedLists.includes(list.id)}
                                            onChange={() => toggleSelectList(list.id)}
                                            className="checkbox checkbox-primary mt-1"
                                        />
                                        <div>
                                            <div className="flex items-center gap-2">
                                                <h3 className="font-bold text-lg text-gray-900 line-clamp-1">
                                                    {list.name}
                                                </h3>
                                            </div>
                                        </div>
                                    </div>

                                    <div className="dropdown dropdown-end">
                                        <label tabIndex={0} className="btn btn-ghost btn-sm btn-circle">
                                            <FaEllipsisV />
                                        </label>
                                        <ul tabIndex={0} className="dropdown-content menu p-2 shadow bg-base-100 rounded-box w-52">
                                            <li>
                                                <Link to={`/tierlist/${list.id}`} className="gap-2">
                                                    <FaEye />
                                                    Voir
                                                </Link>
                                            </li>
                                            <li>
                                                <button onClick={() => handleExport(list.id)} className="gap-2">
                                                    <FaDownload />
                                                    Exporter
                                                </button>
                                            </li>
                                            <li>
                                                <button
                                                    onClick={() => {
                                                        setActiveListId(list.id);
                                                        setShowShareModal(true);
                                                    }}
                                                    className="gap-2"
                                                >
                                                    <FaShare />
                                                    Partager
                                                </button>
                                            </li>
                                            <div className="divider my-1"></div>
                                            <li>
                                                <button
                                                    onClick={() => {
                                                        setActiveListId(list.id);
                                                        setShowDeleteModal(true);
                                                    }}
                                                    className="gap-2 text-red-500"
                                                >
                                                    <FaTrash />
                                                    Supprimer
                                                </button>
                                            </li>
                                        </ul>
                                    </div>
                                </div>

                                <div className="relative h-48 overflow-hidden">
                                    {list.thumbnail ? (
                                        <img
                                            src={list.thumbnail}
                                            alt={list.title}
                                            className="w-full h-full object-cover hover:scale-105 transition-transform duration-300"
                                        />
                                    ) : (
                                        <div className="w-full h-full bg-gradient-to-br from-gray-200 to-gray-300 flex items-center justify-center">
                                            <FaChartBar className="text-gray-400 text-4xl" />
                                        </div>
                                    )}
                                </div>

                                <div className="p-4">
                                    <div className="flex justify-between items-center mb-3">
                                        <span className="badge badge-outline">{list.category}</span>
                                        <div className="text-sm text-gray-500">
                                            <FaCalendar className="inline mr-1" />
                                            {new Date(list.updatedAt).toLocaleDateString()}
                                        </div>
                                    </div>

                                    <div className="flex gap-2 mt-4">
                                        <Link
                                            to={`/tierlist/${list.id}`}
                                            className="btn btn-primary btn-sm flex-1"
                                        >
                                            <FaEye className="mr-1" />
                                            Voir
                                        </Link>
                                    </div>
                                </div>
                            </div>
                        ))}
                    </div>
                ) : (
                    <div className="text-center py-16">
                        <div className="text-6xl mb-4">📊</div>
                        <h3 className="text-2xl font-semibold text-gray-700 mb-2">
                            Aucune tier list trouvée
                        </h3>
                        <p className="text-gray-500 mb-8 max-w-md mx-auto">
                            {searchQuery
                                ? "Aucune tier list ne correspond à votre recherche. Essayez d'autres termes."
                                : "Commencez par créer votre première tier list !"
                            }
                        </p>
                        <Link to="/tierlist/new" className="btn btn-primary gap-2">
                            <FaPlus />
                            Créer ma première tier list
                        </Link>
                    </div>
                )}

                {filteredLists.length > 0 && (
                    <div className="flex justify-center mt-8">
                        <div className="join">
                            <button className="join-item btn btn-outline">«</button>
                            <button className="join-item btn btn-active">1</button>
                            <button className="join-item btn btn-outline">2</button>
                            <button className="join-item btn btn-outline">3</button>
                            <button className="join-item btn btn-outline">4</button>
                            <button className="join-item btn btn-outline">»</button>
                        </div>
                    </div>
                )}
            </div>

            {showDeleteModal && <DeleteModal />}
            {showShareModal && <ShareModal />}

            <style jsx>{`
                .line-clamp-1 {
                    display: -webkit-box;
                    -webkit-line-clamp: 1;
                    -webkit-box-orient: vertical;
                    overflow: hidden;
                }
                .line-clamp-2 {
                    display: -webkit-box;
                    -webkit-line-clamp: 2;
                    -webkit-box-orient: vertical;
                    overflow: hidden;
                }
            `}</style>
        </div>
    );
};

export default MyTierListsPage;