import {useDraggable} from "@dnd-kit/core";

const SortableItem = ({id, item, handleRemove}) => {
    const {attributes, listeners, setNodeRef, transform, isDragging} = useDraggable({
        id,
        data: {type: 'item', item}
    });

    const style = transform ? {
        transform: `translate3d(${transform.x}px, ${transform.y}px, 0)`,
        zIndex: 1000,
    } : {};

    return (
        <div
            ref={setNodeRef}
            style={style}
            {...attributes}
            {...listeners}
            className={`relative group cursor-grab active:cursor-grabbing ${isDragging ? 'opacity-50' : ''}`}
        >
            <img
                src={item.imageUrl}
                alt={item.title}
                title={item.title}
                className="w-16 h-16 object-cover"
            />
            <button
                onClick={(e) => {
                    e.stopPropagation();
                    handleRemove(id);
                }}
                className="absolute -top-1 -right-1 bg-red-500 text-white rounded-full w-5 h-5 text-xs opacity-0 group-hover:opacity-100 transition-opacity"
            >
                ×
            </button>
        </div>
    );
};

export default SortableItem;