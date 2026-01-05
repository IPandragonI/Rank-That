import {useDraggable} from "@dnd-kit/core";

const GalleryItem = ({item, handleAddToTier}) => {
    const {attributes, listeners, setNodeRef, transform, isDragging} = useDraggable({
        id: `gallery-${item.id}`,
        data: {type: 'gallery', item}
    });

    const style = transform ? {
        transform: `translate3d(${transform.x}px, ${transform.y}px, 0)`,
    } : {};

    return (
        <div
            ref={setNodeRef}
            style={style}
            {...attributes}
            {...listeners}
            className={`relative cursor-grab active:cursor-grabbing group ${isDragging ? 'opacity-50' : ''}`}
        >
            <img
                src={item.imageUrl}
                alt={item.title}
                title={item.title}
                className="w-16 h-16 object-cover hover:scale-105 transition-transform"
            />
            <div
                className="absolute bottom-0 left-0 right-0 bg-black/75 text-white text-xs p-1 text-center truncate opacity-0 group-hover:opacity-100 transition-opacity">
                {item.title}
            </div>
        </div>
    );
};

export default GalleryItem;